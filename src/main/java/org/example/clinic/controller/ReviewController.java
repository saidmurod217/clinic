package org.example.clinic.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic.dto.request.ReviewRequest;
import org.example.clinic.dto.response.ReviewResponse;
import org.example.clinic.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> create(@RequestBody ReviewRequest dto) {
        return ResponseEntity.ok(reviewService.createReview(dto));
    }

    @PostMapping("/clinic/{clinicId}")
    public ResponseEntity<ReviewResponse> createForClinic(
            @PathVariable Integer clinicId,
            @RequestBody ReviewRequest dto) {
        return ResponseEntity.ok(reviewService.createReviewForClinic(dto, clinicId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> get(@PathVariable Integer id) {
        return ResponseEntity.ok(reviewService.getReview(id));
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAll() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> update(@PathVariable Integer id,
                                                    @RequestBody ReviewRequest dto) {
        return ResponseEntity.ok(reviewService.updateReview(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
