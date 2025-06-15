package org.example.clinic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.clinic.enums.UserRole;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";
    private Integer userId;
    private String username;
    private String email;
    private UserRole role;

    public AuthResponse(String token, Integer userId, String username, String email, UserRole role) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}