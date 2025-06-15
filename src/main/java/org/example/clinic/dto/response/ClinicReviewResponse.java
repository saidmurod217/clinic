package org.example.clinic.dto.response;

import lombok.Data;

@Data
public class ClinicReviewResponse {
    private Integer id;
    private Integer reviewId;
    private Integer clinicId;
}