package org.example.clinic.dto.request;

import lombok.Data;

@Data
public class ReviewRequest {
    private Integer userId;
    private String content;
    private Float starCount;
}