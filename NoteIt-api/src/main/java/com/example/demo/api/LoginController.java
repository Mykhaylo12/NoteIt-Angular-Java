package com.example.demo.api;

import com.example.demo.api.viewmodel.UserViewModel;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.Mapper;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/login")
public class LoginController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final Mapper mapper;

    public LoginController(UserService userService, AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider, Mapper mapper) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody UserViewModel loginViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        User loginUser = mapper.convertToUser(loginViewModel);
        String token;
        User user;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getEmail(),
                    loginUser.getPassword()));
            user = userService.findByEmail(loginUser.getEmail());
            if (user == null) {
                return new ResponseEntity("Could't find user with email " +
                        loginUser.getEmail(), HttpStatus.BAD_REQUEST);
            }
            token = jwtTokenProvider.createToken(loginUser.getEmail(), user.getRoles());
        } catch (AuthenticationException e) {
            return new ResponseEntity("Invalid email or password", HttpStatus.BAD_REQUEST);
        }
        return getResponseEntity(token, user);
    }

    private ResponseEntity getResponseEntity(String token, User user) {
        List<String> roles = mapper.convertUserRolesToString(user.getRoles());
        Map<String, Object> response = new HashMap<>();
        response.put("email", user.getEmail());
        response.put("token", token);
        response.put("id", String.valueOf(user.getId()));
        response.put("username", user.getUsername());
        response.put("roles", roles);
        return ResponseEntity.ok(response);
    }
}
