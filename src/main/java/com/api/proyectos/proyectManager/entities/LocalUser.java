package com.api.proyectos.proyectManager.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class LocalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String name;
    private String lastname;
    private String email;
    private Integer number;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL)
    @Column(name = "projects")
    private List<Project> projects = new ArrayList<>();
}
