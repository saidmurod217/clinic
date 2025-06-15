package org.example.clinic.dto.response;

import lombok.Data;

import java.time.Instant;

@Data
public class ClinicSummaryResponse {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private Float rating;
    private Instant since;
    private Integer reviewCount;
    private Integer doctorCount;
}