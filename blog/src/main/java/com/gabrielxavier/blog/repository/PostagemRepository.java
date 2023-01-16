package com.gabrielxavier.blog.repository;

import com.gabrielxavier.blog.model.Postagem;
import com.gabrielxavier.blog.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository  extends JpaRepository<Postagem, Long> {
    Postagem findPostagemByTitulo(String titulo);
}
