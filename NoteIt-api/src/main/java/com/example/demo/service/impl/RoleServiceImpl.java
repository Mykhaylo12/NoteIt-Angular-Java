package com.example.demo.service.impl;

import com.example.demo.db.RoleRepository;
import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findByName(String role_user) {
        return roleRepository.findByName(role_user);
    }
}
