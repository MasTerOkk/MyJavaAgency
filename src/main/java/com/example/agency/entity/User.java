package com.example.agency.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
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
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tour> favTourList;

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role, favTourList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return  Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(favTourList, user.favTourList);
    }

}
