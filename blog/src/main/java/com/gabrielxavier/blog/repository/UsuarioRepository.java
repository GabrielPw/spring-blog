package com.gabrielxavier.blog.repository;

import com.gabrielxavier.blog.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
