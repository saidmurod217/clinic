package org.example.clinic.dto.response;

import lombok.Data;

@Data
public class ServiceResponse {
    private Integer id;
    private String name;
    private Float cost;
    private String info;
    private Integer clinicId;
}