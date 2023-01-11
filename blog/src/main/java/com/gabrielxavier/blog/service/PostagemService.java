package com.gabrielxavier.blog.service;

import com.gabrielxavier.blog.dto.UsuarioDto;
import com.gabrielxavier.blog.model.Postagem;
import com.gabrielxavier.blog.model.Role;
import com.gabrielxavier.blog.model.Usuario;
import com.gabrielxavier.blog.repository.PostagemRepository;
import com.gabrielxavier.blog.repository.RoleRepository;
import com.gabrielxavier.blog.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PostagemService {

    @Autowired
    PostagemRepository postagemRepository;

    public List<Postagem> listarTodos(){
        return postagemRepository.findAll();
    }

    public Optional<Postagem> obterPorId(Long id){
        return postagemRepository.findById(id);
    }

    public boolean existePorId(Long id){
        return postagemRepository.existsById(id);
    }

    public Postagem cadastrarPostagem(Postagem postagem) {

        postagemRepository.save(postagem);
        return postagem;
    }
}
