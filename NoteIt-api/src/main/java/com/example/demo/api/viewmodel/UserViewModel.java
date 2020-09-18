package com.example.demo.api.viewmodel;

import lombok.Data;

@Data
public class UserViewModel {
    private String id;
    private String username;
    private String token;
    private String email;
    private String password;
}
