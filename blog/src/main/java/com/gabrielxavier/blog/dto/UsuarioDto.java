package com.gabrielxavier.blog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank(message = "Campo email não pode estar vazio.")
    private String email;
    @NotBlank
    private String password;
}
