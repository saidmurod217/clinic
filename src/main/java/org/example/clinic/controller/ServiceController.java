package org.example.clinic.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic.dto.request.ServiceRequest;
import org.example.clinic.dto.response.ServiceResponse;
import org.example.clinic.service.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;

    // Create a new service and associate it with the current user's clinic
    @PostMapping
    public ResponseEntity<ServiceResponse> createService(@RequestBody ServiceRequest request) {
        ServiceResponse response = serviceService.createService(request);
        return ResponseEntity.ok(response);
    }

    // Get all services for the current user's clinic
    @GetMapping
    public ResponseEntity<List<ServiceResponse>> getServicesByClinic() {
        List<ServiceResponse> services = serviceService.getServicesByClinic();
        return ResponseEntity.ok(services);
    }

    // Update a service belonging to the current user's clinic
    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse> updateService(@PathVariable Integer id, @RequestBody ServiceRequest request) {
        ServiceResponse response = serviceService.updateService(id, request);
        return ResponseEntity.ok(response);
    }

    // Delete a service from the current user's clinic
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Integer id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
