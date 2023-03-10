package com.gabrielxavier.blog.repository;

import com.gabrielxavier.blog.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmailAndPassword(String email, String password);
    Usuario findByName(String name);
    Usuario findByEmail(String email);
}
