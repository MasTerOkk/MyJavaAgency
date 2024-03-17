package com.example.agency.controllers;

import com.example.agency.entity.Role;
import com.example.agency.entity.User;
import com.example.agency.service.RoleService;
import com.example.agency.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    // TODO Обработать error "RoleNotFoundException"
    @PostMapping("/addUser")
    public String createUser(@ModelAttribute(name = "user") User user) throws RoleNotFoundException {
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/newRole")
    public String newRole(Model model) {
        model.addAttribute("role", new Role());
        return "role";
    }

    @PostMapping("/addRole")
    public String createRole(@ModelAttribute(name = "role") Role role) {
        roleService.saveRole(role);
        return "redirect:/";
    }

}
