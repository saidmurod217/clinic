package org.example.clinic.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class RoomUpdateRequest {
    private String title;
    private Float price;
     List<Integer> imageIds;

    // Getters and Setters
}