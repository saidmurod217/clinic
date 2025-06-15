package org.example.clinic.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic.dto.request.ClinicRequest;
import org.example.clinic.dto.response.ClinicResponse;
import org.example.clinic.entity.WorkingHour;
import org.example.clinic.service.ClinicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinics")
@RequiredArgsConstructor
public class ClinicController {

    private final ClinicService clinicService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<ClinicResponse> create(@RequestBody ClinicRequest request, @PathVariable Integer userId) {
        return ResponseEntity.ok(clinicService.createClinic(request, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicResponse> get(@PathVariable Integer id) {
        return ResponseEntity.ok(clinicService.getClinic(id));
    }

    @GetMapping
    public ResponseEntity<List<ClinicResponse>> getAll() {
        return ResponseEntity.ok(clinicService.getAllClinics());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicResponse> update(@PathVariable Integer id, @RequestBody ClinicRequest request) {
        return ResponseEntity.ok(clinicService.updateClinic(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clinicService.deleteClinic(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{clinicId}/assign-doctor/{doctorId}")
    public void assignDoctor(@PathVariable Integer clinicId, @PathVariable Integer doctorId) {
        clinicService.assignDoctor(clinicId, doctorId);
    }

    @PostMapping("/{clinicId}/add-working-hour")
    public void addWorkingHour(@PathVariable Integer clinicId, @RequestBody WorkingHour wh) {
        clinicService.addWorkingHour(clinicId, wh);
    }
}