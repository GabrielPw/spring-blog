package com.gabrielxavier.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

//    @Column(nullable = false, columnDefinition = "TINYINT(1)")
//    private boolean enabled;

    private String role;

    public Long getId() {
        return id;
    }
}
