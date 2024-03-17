package com.example.agency.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
//    @ManyToOne
//    @JoinColumn(name = "tourid")
//    private Tour tour;
//    @ManyToOne
//    @JoinColumn(name = "userid")
//    private User user;
}
