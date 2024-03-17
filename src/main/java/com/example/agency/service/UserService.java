package com.example.agency.service;

import com.example.agency.entity.User;
import com.example.agency.entity.Role;
import com.example.agency.repositories.RoleRepository;
import com.example.agency.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public void saveUser(User user) throws RoleNotFoundException {
        Role role = roleRepository.getByRoleName("ROLE_USER")
                .orElseThrow(() -> new RoleNotFoundException("role not found"));

        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
