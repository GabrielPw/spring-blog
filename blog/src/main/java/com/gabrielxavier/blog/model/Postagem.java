package com.gabrielxavier.blog.model;

import com.gabrielxavier.blog.enuns.CategoriaPostagemEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.Normalizer;
import java.util.regex.Pattern;

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
    private String dataUltimaAtualizacao;

    public String removeAccentFromTitulo(){

        titulo = titulo.replace(" ", "-");
        titulo.replaceAll("[^A-Za-z0-9\\-]", "-");

        String nfdNormalizedString = Normalizer.normalize(titulo, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");


        System.out.println("Método removeAccentFromTitulo: " + pattern.matcher(nfdNormalizedString).replaceAll("") + "\n\n");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

}
