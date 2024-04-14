package com.example.agency.repositories;

import com.example.agency.entity.Tour;
import com.example.agency.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

//    @Query("SELECT u from User u join fetch u.favTourList where u.login = ?1")
//    Optional<User> findByLoginEager(String login);
//    @Query("SELECT u from User u join fetch u.favTourList where u.id = ?1")
//    Optional<User> findByIdEager(Long id);

    @Query("SELECT u FROM User u WHERE u = ?1")
    Optional<User> getByLogin(String login);

}
