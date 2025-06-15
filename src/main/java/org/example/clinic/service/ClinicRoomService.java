package org.example.clinic.service;

import lombok.RequiredArgsConstructor;
import org.example.clinic.dto.request.ClinicRoomRequest;
import org.example.clinic.dto.response.ClinicRoomResponse;
import org.example.clinic.entity.Clinic;
import org.example.clinic.entity.ClinicRoom;
import org.example.clinic.entity.Room;
import org.example.clinic.repository.ClinicRepository;
import org.example.clinic.repository.ClinicRoomRepository;
import org.example.clinic.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClinicRoomService {

    private final ClinicRoomRepository clinicRoomRepository;
    private final ClinicRepository clinicRepository;
    private final RoomRepository roomRepository;

    public ClinicRoomResponse create(ClinicRoomRequest request) {
        Clinic clinic = clinicRepository.findById(request.getClinicId())
                .orElseThrow(() -> new RuntimeException("Clinic not found"));
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        ClinicRoom cr = new ClinicRoom();
        cr.setClinic(clinic);
        cr.setRoom(room);

        return mapToResponse(clinicRoomRepository.save(cr));
    }

    public List<ClinicRoomResponse> getAll() {
        return clinicRoomRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        clinicRoomRepository.deleteById(id);
    }

    private ClinicRoomResponse mapToResponse(ClinicRoom cr) {
        ClinicRoomResponse res = new ClinicRoomResponse();
        res.setId(cr.getId());
        res.setClinic(cr.getClinic());
        res.setRoom(cr.getRoom());
        return res;
    }
}
