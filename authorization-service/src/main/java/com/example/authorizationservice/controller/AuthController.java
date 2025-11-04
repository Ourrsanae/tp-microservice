package com.example.authorizationservice.controller;

import com.example.authorizationservice.model.UserCredential;
import com.example.authorizationservice.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestHeader("username") String username,
                             @RequestHeader("password") String password) {
        UserCredential user = authService.authenticate(username, password);

        if (user == null)
            return "UNAUTHORIZED";
        else
            return user.getRole(); // ADMIN ou USER
    }
}
