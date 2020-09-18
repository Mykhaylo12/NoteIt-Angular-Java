package com.example.demo.api;

import com.example.demo.api.viewmodel.UserViewModel;
import com.example.demo.model.User;
import com.example.demo.service.Mapper;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final Mapper mapper;

    public UserController(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<UserViewModel> getAllUsers() {
        List<User> users = userService.getAllUser();
        return users.stream().map(mapper::convertToUserViewModel).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        userService.deleteById(Long.parseLong(id));
    }
}
