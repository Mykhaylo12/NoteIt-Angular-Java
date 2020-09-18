package com.example.demo.db;

import com.example.demo.cofig.BCryptPasswordEncoder;
import com.example.demo.model.Notebook;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbSeeder {
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final String userPassword = "12345";

    public DbSeeder(BCryptPasswordEncoder passwordEncoder, RoleService roleService, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    public void seedUserRoles() {
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleService.save(roleUser);
        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleService.save(roleAdmin);
    }

    public void seedUserWithUserRole() {
        Role roleUser = roleService.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);

        User userOnly = new User();
        userOnly.setRoles(roles);
        userOnly.setPassword(passwordEncoder.encode(userPassword));
        userOnly.setUsername("userOnly");
        userOnly.setEmail("userOnly@gmail.com");
        userRepository.save(userOnly);
    }

    public void seedUserWithAdminRole() {
        Role roleAdmin = roleService.findByName("ROLE_ADMIN");
        List<Role> roles = new ArrayList<>();
        roles.add(roleAdmin);

        User adminOnly = new User();
        adminOnly.setRoles(roles);
        adminOnly.setPassword(passwordEncoder.encode(userPassword));
        adminOnly.setUsername("adminOnly");
        adminOnly.setEmail("adminOnly@gmail.com");
        userRepository.save(adminOnly);
    }

    public void seedUserWithAdminAndUserRoles() {
        Role roleAdmin = roleService.findByName("ROLE_ADMIN");
        Role roleUser = roleService.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(roleAdmin);
        roles.add(roleUser);

        User adminAndUser = new User();
        adminAndUser.setRoles(roles);
        adminAndUser.setPassword(passwordEncoder.encode(userPassword));
        adminAndUser.setUsername("adminAndUser");
        adminAndUser.setEmail("adminAndUser@gmail.com");
        userRepository.save(adminAndUser);
    }
}
