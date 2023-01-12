package com.gabrielxavier.blog.controller;

import com.gabrielxavier.blog.dto.UsuarioDto;
import com.gabrielxavier.blog.model.Postagem;
import com.gabrielxavier.blog.model.Role;
import com.gabrielxavier.blog.model.Usuario;
import com.gabrielxavier.blog.repository.PostagemRepository;
import com.gabrielxavier.blog.repository.RoleRepository;
import com.gabrielxavier.blog.repository.UsuarioRepository;
import com.gabrielxavier.blog.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class PostagemController {
    @Autowired
    PostagemRepository postagemRepo;
    @GetMapping("/")
    public ModelAndView home(Model model){

        System.out.println("\n\n\nHome...");

        List<Postagem> postagens = postagemRepo.findAll();

        ModelAndView mv = new ModelAndView("home.html");
        mv.addObject("postagens", postagens);
        return mv;
    }

    @GetMapping("postagem/{id}")
    public ModelAndView obterPorId(@PathVariable Long id){

        Optional<Postagem> postagem = postagemRepo.findById(id);
        ModelAndView mv = new ModelAndView("postagem_info");

        if (postagem.isPresent()){
            mv.addObject("postagem", postagem.get());
        } else {
            mv.addObject("postagem", null);
        }

        return mv;
    }

    public boolean existePorId(Long id){
        return postagemRepo.existsById(id);
    }

    @PostMapping("postagem/new")
    public String salvarPostagem(@RequestParam String titulo, @RequestParam String conteudo, @RequestParam String urlFotoCapa){

        Postagem postagem = new Postagem();
        postagem.setTitulo(titulo);
        postagem.setConteudo(conteudo);
        postagem.setUrlFotoCapa(urlFotoCapa);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String data = java.time.LocalDate.now().format(formatter).toString();
        postagem.setDataPostagem(data);

        postagemRepo.save(postagem);
        return "redirect:/";
    }

}
