package com.gabrielxavier.blog.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
