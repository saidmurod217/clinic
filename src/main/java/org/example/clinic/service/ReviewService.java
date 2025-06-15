package org.example.clinic.service;

import lombok.RequiredArgsConstructor;
import org.example.clinic.dto.request.ReviewRequest;
import org.example.clinic.dto.response.ReviewResponse;
import org.example.clinic.entity.Clinic;
import org.example.clinic.entity.ClinicReview;
import org.example.clinic.entity.Review;
import org.example.clinic.entity.User;
import org.example.clinic.repository.ClinicRepository;
import org.example.clinic.repository.ClinicReviewRepository;
import org.example.clinic.repository.ReviewRepository;
import org.example.clinic.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional // ADD THIS ANNOTATION
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ClinicReviewRepository clinicReviewRepository;
    private final ClinicRepository clinicRepository; // ADD THIS DEPENDENCY

    // ADD THIS NEW METHOD
    public ReviewResponse createReviewForClinic(ReviewRequest dto, Integer clinicId) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new RuntimeException("Clinic not found"));

        // Create the review
        Review review = new Review();
        review.setUser(user);
        review.setContent(dto.getContent());
        review.setStarCount(dto.getStarCount());
        review.setCreatedAt(Instant.now());

        Review savedReview = reviewRepository.save(review);

        // Create the clinic-review association
        ClinicReview clinicReview = new ClinicReview();
        clinicReview.setClinic(clinic);
        clinicReview.setReview(savedReview);
        clinicReviewRepository.save(clinicReview);

        // Update clinic rating
        updateClinicRating(clinic);

        return toDTO(savedReview);
    }

    // ADD THIS NEW METHOD
    public void associateReviewWithClinic(Integer reviewId, Integer clinicId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new RuntimeException("Clinic not found"));

        // Check if association already exists
        boolean exists = clinicReviewRepository.findAll().stream()
                .anyMatch(cr -> cr.getReview().getId().equals(reviewId) &&
                        cr.getClinic().getId().equals(clinicId));

        if (!exists) {
            ClinicReview clinicReview = new ClinicReview();
            clinicReview.setClinic(clinic);
            clinicReview.setReview(review);
            clinicReviewRepository.save(clinicReview);

            // Update clinic rating
            updateClinicRating(clinic);
        }
    }

    // ADD THIS NEW METHOD
    private void updateClinicRating(Clinic clinic) {
        List<ClinicReview> clinicReviews = clinicReviewRepository.findAll().stream()
                .filter(cr -> cr.getClinic().getId().equals(clinic.getId()))
                .collect(Collectors.toList());

        if (!clinicReviews.isEmpty()) {
            double averageRating = clinicReviews.stream()
                    .mapToDouble(cr -> cr.getReview().getStarCount())
                    .average()
                    .orElse(0.0);

            clinic.setRating((float) averageRating);
            clinicRepository.save(clinic);
        }
    }

    // KEEP YOUR EXISTING METHODS AS THEY ARE
    public ReviewResponse createReview(ReviewRequest dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Review review = new Review();
        review.setUser(user);
        review.setContent(dto.getContent());
        review.setStarCount(dto.getStarCount());
        review.setCreatedAt(Instant.now());

        Review saved = reviewRepository.save(review);
        return toDTO(saved);
    }

    public ReviewResponse getReview(Integer id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return toDTO(review);
    }

    public List<ReviewResponse> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ReviewResponse updateReview(Integer id, ReviewRequest dto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setContent(dto.getContent());
        review.setStarCount(dto.getStarCount());
        Review updated = reviewRepository.save(review);

        return toDTO(updated);
    }

    public void deleteReview(Integer id) {
        reviewRepository.deleteById(id);
    }

    private ReviewResponse toDTO(Review review) {
        ReviewResponse dto = new ReviewResponse();
        dto.setId(review.getId());
        dto.setUserId(review.getUser().getId());
        dto.setContent(review.getContent());
        dto.setStarCount(review.getStarCount());
        dto.setCreatedAt(review.getCreatedAt());
        return dto;
    }
}