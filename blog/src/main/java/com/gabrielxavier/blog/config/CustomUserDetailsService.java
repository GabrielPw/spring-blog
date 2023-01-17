package com.gabrielxavier.blog.config;

import com.gabrielxavier.blog.model.Usuario;
import com.gabrielxavier.blog.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(usuario);
    }
}
