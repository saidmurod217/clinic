package org.example.clinic.dto.request;

import lombok.Data;

@Data
public class ClinicReviewRequest {
    private Integer reviewId;
    private Integer clinicId;
}
