package org.example.clinic.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic.dto.request.ClinicRoomRequest;
import org.example.clinic.dto.response.ClinicRoomResponse;
import org.example.clinic.service.ClinicRoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinic-rooms")
@RequiredArgsConstructor
public class ClinicRoomController {

    private final ClinicRoomService clinicRoomService;

    @PostMapping
    public ClinicRoomResponse create(@RequestBody ClinicRoomRequest request) {
        return clinicRoomService.create(request);
    }

    @GetMapping
    public List<ClinicRoomResponse> getAll() {
        return clinicRoomService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        clinicRoomService.delete(id);
    }
}
