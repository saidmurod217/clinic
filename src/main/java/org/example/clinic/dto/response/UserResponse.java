package org.example.clinic.dto.response;

import lombok.Data;
import org.example.clinic.enums.UserRole;

@Data
public class UserResponse {
    private Integer id;
    private String username;
    private String email;
    private String phone;
    private UserRole role;
}