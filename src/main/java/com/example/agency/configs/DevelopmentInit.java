package com.example.agency.configs;

import com.example.agency.entity.Role;
import com.example.agency.entity.Tour;
import com.example.agency.entity.User;
import com.example.agency.repositories.RoleRepository;
import com.example.agency.repositories.TourRepository;
import com.example.agency.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class DevelopmentInit {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private TourRepository tourRepository;
    private PasswordEncoder encoder;

    @PostConstruct
    private void postConstruct() {
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");
        roleRepository.saveAll(List.of(admin,user));

        User user1 = new User();
        user1.setFavTourList(null);
        user1.setPassword(encoder.encode("test"));
        user1.setRole(user);
        user1.setLogin("test");
        userRepository.save(user1);

        Tour tour1 = new Tour();
        tour1.setName("Tour1");
        tour1.setPrice(10000);
        Tour tour2 = new Tour();
        tour2.setName("Tour2");
        tour2.setPrice(9999);
        tourRepository.saveAll(List.of(tour1,tour2));
    }
}
