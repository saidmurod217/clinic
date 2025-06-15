// --- WorkingHourController.java ---
package org.example.clinic.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic.dto.request.WorkingHourRequest;
import org.example.clinic.dto.response.WorkingHourResponse;
import org.example.clinic.service.WorkingHourService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/working-hours")
@RequiredArgsConstructor
public class WorkingHourController {

    private final WorkingHourService workingHourService;

    @PostMapping("/clinic/{clinicId}")
    public ResponseEntity<WorkingHourResponse> create(@PathVariable Integer clinicId, @RequestBody WorkingHourRequest request) {
        return ResponseEntity.ok(workingHourService.createWorkingHour(clinicId, request));
    }

    @GetMapping
    public ResponseEntity<List<WorkingHourResponse>> getAll() {
        return ResponseEntity.ok(workingHourService.getAllWorkingHours());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkingHourResponse> get(@PathVariable Integer id) {
        return ResponseEntity.ok(workingHourService.getWorkingHour(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkingHourResponse> update(@PathVariable Integer id, @RequestBody WorkingHourRequest request) {
        return ResponseEntity.ok(workingHourService.updateWorkingHour(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        workingHourService.deleteWorkingHour(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{workingHourId}/assign-doctor/{doctorId}")
    public ResponseEntity<Void> assignDoctor(@PathVariable Integer workingHourId, @PathVariable Integer doctorId) {
        workingHourService.assignDoctorToWorkingHour(doctorId, workingHourId);
        return ResponseEntity.ok().build();
    }
}
