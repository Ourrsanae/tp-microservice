package com.example.authorizationservice.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredential {
    private String username;
    private String password;
    private String role;
}
