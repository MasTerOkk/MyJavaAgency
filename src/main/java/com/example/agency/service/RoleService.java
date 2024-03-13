package com.example.agency.service;

import com.example.agency.entity.Role;
import com.example.agency.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
