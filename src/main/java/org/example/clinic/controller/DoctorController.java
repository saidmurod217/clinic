package org.example.clinic.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic.dto.request.DoctorRequest;
import org.example.clinic.dto.response.DoctorResponse;
import org.example.clinic.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponse> create(@RequestBody DoctorRequest request) {
        return ResponseEntity.ok(doctorService.createDoctor(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> get(@PathVariable Integer id) {
        return ResponseEntity.ok(doctorService.getDoctor(id));
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> getAll() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponse> update(@PathVariable Integer id, @RequestBody DoctorRequest request) {
        return ResponseEntity.ok(doctorService.updateDoctor(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{doctorId}/assign-clinic/{clinicId}")
    public void assignClinic(@PathVariable Integer doctorId, @PathVariable Integer clinicId) {
        doctorService.assignToClinic(doctorId, clinicId);
    }

    @PostMapping("/{doctorId}/assign-working-hour/{whId}")
    public void assignWorkingHour(@PathVariable Integer doctorId, @PathVariable Integer whId) {
        doctorService.assignToWorkingHour(doctorId, whId);
    }

    @PostMapping("/{doctorId}/assign-review/{reviewId}")
    public void assignReview(@PathVariable Integer doctorId, @PathVariable Integer reviewId) {
        doctorService.assignReviewToDoctor(doctorId, reviewId);
    }
}