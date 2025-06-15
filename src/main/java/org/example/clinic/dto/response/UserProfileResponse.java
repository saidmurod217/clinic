package org.example.clinic.dto.response;

import lombok.Data;

@Data
public class UserProfileResponse {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String username;
}