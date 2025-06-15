package org.example.clinic.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClinicServiceRequest {
    @NotNull
    private Integer clinicId;

    @NotNull
    @DecimalMin("0.0")
    private Float cost;

    private String info;
}
