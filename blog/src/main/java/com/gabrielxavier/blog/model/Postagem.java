package com.gabrielxavier.blog.model;

import com.gabrielxavier.blog.enuns.CategoriaPostagemEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "conteudo", columnDefinition = "TEXT")
    private String conteudo;

    @Column(name = "urlfoto", columnDefinition = "TEXT")
    private String urlFotoCapa;
    @Enumerated(EnumType.STRING)
    private CategoriaPostagemEnum categoria;
    private String dataPostagem;


}
