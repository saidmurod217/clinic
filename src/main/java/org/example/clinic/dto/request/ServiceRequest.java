package org.example.clinic.dto.request;

import lombok.Data;

@Data
public class ServiceRequest {
    private String name;
    private Float cost;
    private String info;
}