package org.example.clinic.dto.request;

import lombok.Data;

@Data
public class DoctorRequest {
    private Integer userId;
    private String name;
    private String surname;
    private String bio;
    private String certificates;
}