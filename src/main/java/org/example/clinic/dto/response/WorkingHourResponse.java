package org.example.clinic.dto.response;

import lombok.Data;

import java.time.Instant;

@Data
public class WorkingHourResponse {
    private Integer id;
    private Instant fromTime;
    private Instant toTime;
    private Integer clinicId;
}