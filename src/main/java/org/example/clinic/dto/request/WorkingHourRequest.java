package org.example.clinic.dto.request;

import lombok.Data;

import java.time.Instant;

@Data
public class WorkingHourRequest {
    private Instant fromTime;
    private Instant toTime;
}