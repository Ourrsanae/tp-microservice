package com.example.authorizationservice.service;

import com.example.authorizationservice.model.UserCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Value("${security.productcomposite.admin.username}")
    private String adminUsername;
    @Value("${security.productcomposite.admin.password}")
    private String adminPassword;

    @Value("${security.productcomposite.user.username}")
    private String userUsername;
    @Value("${security.productcomposite.user.password}")
    private String userPassword;

    public UserCredential authenticate(String username, String password) {
        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            return new UserCredential(username, password, "ADMIN");
        } else if (username.equals(userUsername) && password.equals(userPassword)) {
            return new UserCredential(username, password, "USER");
        } else {
            return null; // utilisateur non autorisé
        }
    }
}
