package com.example.agency.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Tour {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private Integer price;

}
