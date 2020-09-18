package com.example.demo.api;

import com.example.demo.api.viewmodel.UserViewModel;
import com.example.demo.model.User;
import com.example.demo.service.Mapper;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    private final UserService userService;
    private final Mapper mapper;

    public RegistrationController(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UserViewModel> registration(@RequestBody UserViewModel registrationViewModel,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        User user;
        UserViewModel userDto;
        try {
            user = mapper.convertToUser(registrationViewModel);
            User registerUser = userService.register(user);
            userDto =mapper.convertToUserViewModel(registerUser);// UserDto.toUserDto(registerUser);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userDto);
    }
}
