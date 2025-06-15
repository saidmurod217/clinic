package org.example.clinic.dto.request;

import lombok.Data;

@Data
public class ClinicRoomRequest {
    private Integer clinicId;
    private Integer roomId;
}