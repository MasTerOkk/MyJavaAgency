package com.example.agency.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
public class Tour {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private Integer price;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> usersFavTourList;

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, usersFavTourList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return  Objects.equals(id, tour.id) &&
                Objects.equals(name, tour.name) &&
                Objects.equals(price, tour.price) &&
                Objects.equals(usersFavTourList, tour.usersFavTourList);
    }

}
