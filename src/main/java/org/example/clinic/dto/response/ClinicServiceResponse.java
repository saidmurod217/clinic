package org.example.clinic.dto.response;

import lombok.Data;

@Data
public class ClinicServiceResponse {
    private Integer id;
    private ServiceResponse service;
    private Float cost;
    private String info;
}