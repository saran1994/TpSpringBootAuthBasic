package com.kalanso.coaching.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class OurUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private TypeRole roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> tickets;

    // Getters and setters
}
