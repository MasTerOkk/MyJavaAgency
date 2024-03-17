package com.example.agency.repositories;

import com.example.agency.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String roleUser);
    @Query("SELECT r FROM Role r where r.name = ?1")
    Optional<Role> getByRoleName(String roleUser);

}
