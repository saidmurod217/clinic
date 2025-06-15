package org.example.clinic.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class RoomResponse {
    private Integer id;
    private String title;
    private Float price;
    private List<String> imageUrls;

    // Getters and Setters
}
