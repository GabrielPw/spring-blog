package com.gabrielxavier.blog.controller;

import com.gabrielxavier.blog.dto.UsuarioDto;
import com.gabrielxavier.blog.model.Role;
import com.gabrielxavier.blog.model.Usuario;
import com.gabrielxavier.blog.repository.RoleRepository;
import com.gabrielxavier.blog.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepo;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/login")
    public String doLogin(@RequestParam String email, @RequestParam String senha){

        Usuario user = usuarioRepo.findByEmailAndPassword(email, senha);

        if(user == null){

        }
        return "d";
    }

    @GetMapping("/usuario/all")
    public ModelAndView listarTodos(){

        System.out.println("\nListar...");
        ModelAndView mv = new ModelAndView("home.html");

        List<Usuario> usuarios =  usuarioRepo.findAll();
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    @GetMapping("usuario/{id}")
    public Optional<Usuario> obterPorId(@PathVariable Long id){
        return usuarioRepo.findById(id);
    }

    public boolean existePorId(Long id){
        return usuarioRepo.existsById(id);
    }

    public Usuario cadastrarUsuario(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario();

        usuario.setId(usuarioDto.getId());
        usuario.setName(usuarioDto.getName());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setPassword(usuarioDto.getPassword());

        Role role = roleRepository.findRoleByName("USER");

        if (existePorId(usuario.getId())){
            return null;
        }
        usuarioRepo.save(usuario);
        return usuario;
    }
}
