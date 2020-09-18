package com.example.demo.service;

import com.example.demo.model.Role;

public interface RoleService {
     Role save(Role role);

     Role findByName(String role_user);
}
