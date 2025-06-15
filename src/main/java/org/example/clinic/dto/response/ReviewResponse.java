package org.example.clinic.dto.response;

import lombok.Data;
import java.time.Instant;

@Data
public class ReviewResponse {
    private Integer id;
    private Integer userId;
    private String content;
    private Float starCount;
    private Instant createdAt;
}