package org.example.clinic.dto.request;


import lombok.Data;

@Data
public class ImageUploadRequest {
    private String imgUrl;
    private Integer roomId;
}

