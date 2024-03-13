package com.example.agency.configs;

import com.example.agency.entity.Role;
import com.example.agency.repositories.RoleRepository;
import com.example.agency.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DevelopmentInit {

    private RoleRepository roleRepository;

    @PostConstruct
    private void postConstruct() {
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");
        roleRepository.saveAll(List.of(admin,user));
    }
}
