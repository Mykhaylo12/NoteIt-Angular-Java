package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    User register(User user);

    User findByEmail(String email);

    void deleteById(Long id);

    User getUserById(Long userId);

    List<User> getAllUser();
}
