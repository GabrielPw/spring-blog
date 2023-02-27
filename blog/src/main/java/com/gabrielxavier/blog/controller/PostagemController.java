package com.gabrielxavier.blog.controller;

import com.gabrielxavier.blog.dto.UsuarioDto;
import com.gabrielxavier.blog.enuns.CategoriaPostagemEnum;
import com.gabrielxavier.blog.model.Postagem;
import com.gabrielxavier.blog.model.Role;
import com.gabrielxavier.blog.model.Usuario;
import com.gabrielxavier.blog.repository.PostagemRepository;
import com.gabrielxavier.blog.repository.RoleRepository;
import com.gabrielxavier.blog.repository.UsuarioRepository;
import com.gabrielxavier.blog.service.PostagemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.Normalizer;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Controller
public class PostagemController {
    @Autowired
    PostagemRepository postagemRepo;

    @GetMapping("/notFound")
    public ModelAndView notFound(){

        ModelAndView mv = new ModelAndView("notFound");
        return mv;
    }

    @GetMapping("/")
    public ModelAndView home(@AuthenticationPrincipal UserDetails user){

        System.out.println("\n\n\nHome...");

        List<Postagem> postagens = postagemRepo.findAll();

        ModelAndView mv = new ModelAndView("home.html");
        mv.addObject("postagens", postagens);
        mv.addObject("user", user);
        return mv;
    }

    @GetMapping("postagem/{titulo}")
    public ModelAndView obterPorTitulo(@PathVariable String titulo){

        System.out.println("\nTitulo que chegou do método: " + titulo);

        if (postagemRepo.findPostagemByTitulo(titulo) == null) {
            titulo = titulo.replace("-", " ");
        }
//        titulo.replaceAll("[^A-Za-z0-9\\-]", "-");

        // Remover acentuos (para não termos problemas com criação na url).

        titulo = deAccent(titulo);
        System.out.println("\nTitulo: " + titulo);

        Postagem postagem = postagemRepo.findPostagemByTitulo(titulo);
        ModelAndView mv = new ModelAndView("postagem_info");

        if (postagem != null){
            mv.addObject("postagem", postagem);
        } else {
            mv.addObject("postagem", null);
        }

        return mv;
    }

    public boolean existePorId(Long id){
        return postagemRepo.existsById(id);
    }

    @PostMapping("postagem/new")
    public String salvarPostagem(@RequestParam String titulo, @RequestParam String conteudo, @RequestParam String categoria, @RequestParam String urlFotoCapa){

        Postagem postagem = new Postagem();
        postagem.setTitulo(titulo);
        postagem.setCategoria(CategoriaPostagemEnum.valueOf(categoria));
        postagem.setConteudo(conteudo);
        postagem.setUrlFotoCapa(urlFotoCapa);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String data = java.time.LocalDate.now().format(formatter).toString();
        postagem.setDataPostagem(data);

        postagemRepo.save(postagem);
        return "redirect:/";
    }

    @DeleteMapping
    public void deletarPostagem(Long id){

        postagemRepo.deleteById(id);
        System.out.println("\nPostagem de id: " + id + " deletado.");
    }

    @GetMapping("/postagem/delete/{id}")
    public String deletarPorId(@PathVariable Long id){

        deletarPostagem(id);
        return "redirect:/";
    }

    @PostMapping("/postagem/editar/{id}")
    public String updatePostagem(@PathVariable Long id, @RequestParam String titulo, @RequestParam String conteudo, @RequestParam String urlFotoCapa, @RequestParam String categoria){

        Optional<Postagem> postagemOpt = postagemRepo.findById(id);

        if(postagemOpt.isPresent()){

            Postagem postagem = postagemOpt.get();
            postagem.setTitulo(titulo);
            postagem.setConteudo(conteudo);
            postagem.setUrlFotoCapa(urlFotoCapa);
            postagem.setCategoria(CategoriaPostagemEnum.valueOf(categoria));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String data = java.time.LocalDate.now().format(formatter).toString();
            postagem.setDataUltimaAtualizacao(data);

            postagemRepo.save(postagem);
        }

        // Regex originalmente usado: "[^A-Za-z0-9À-ú\\-]".
        String tituloParaUri = titulo.replaceAll("[^A-Za-z0-9\\-]", "-");

        return "redirect:/postagem/" + postagemOpt.get().removeAccentFromTitulo();
    }

    public static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
