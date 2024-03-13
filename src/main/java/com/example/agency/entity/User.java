package com.example.agency.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(unique = true)
    private String login;
    private String password;
    @ManyToOne
    @JoinColumn(
            name = "roleid",
            nullable = true
    )
    private Role role;

}
