package org.example.clinic.controller;

import org.example.clinic.dto.request.ClinicReviewRequest;
import org.example.clinic.dto.request.ReviewRequest;
import org.example.clinic.dto.response.ClinicReviewResponse;
import org.example.clinic.dto.response.ReviewResponse;
import org.example.clinic.service.ClinicReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clinicReviews")
public class ClinicReviewController {

    private final ClinicReviewService clinicReviewService;

    public ClinicReviewController(ClinicReviewService clinicReviewService) {
        this.clinicReviewService = clinicReviewService;
    }

    // Create new ClinicReview
    @PostMapping
    public ResponseEntity<ClinicReviewResponse> createClinicReview(
            @RequestBody ClinicReviewRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        ClinicReviewResponse created = clinicReviewService.createClinicReview(request, userDetails.getUsername());
        return ResponseEntity.ok(created);
    }

    // Get ClinicReview by id
    @GetMapping("/{id}")
    public ResponseEntity<ClinicReviewResponse> getClinicReviewById(@PathVariable Integer id) {
        Optional<ClinicReviewResponse> review = clinicReviewService.getClinicReviewById(id);
        return review.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // List all ClinicReviews
    @GetMapping
    public ResponseEntity<List<ClinicReviewResponse>> getAllClinicReviews() {
        List<ClinicReviewResponse> reviews = clinicReviewService.getAllClinicReviews();
        return ResponseEntity.ok(reviews);
    }


    // Update ClinicReview by id
    @PutMapping("/{id}")
    public ResponseEntity<ClinicReviewResponse> updateClinicReview(
            @PathVariable Integer id,
            @RequestBody ClinicReviewRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        ClinicReviewResponse updated = clinicReviewService.updateClinicReview(id, request, userDetails.getUsername());
        return ResponseEntity.ok(updated);
    }

    // Delete ClinicReview by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinicReview(@PathVariable Integer id) {
        clinicReviewService.deleteClinicReview(id);
        return ResponseEntity.noContent().build();
    }
}
