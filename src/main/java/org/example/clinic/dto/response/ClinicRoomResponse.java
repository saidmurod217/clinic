package org.example.clinic.dto.response;

import lombok.Data;
import org.example.clinic.entity.Clinic;
import org.example.clinic.entity.Room;

@Data
public class ClinicRoomResponse {
    private Integer id;
    private Clinic clinic;
    private Room room;
}