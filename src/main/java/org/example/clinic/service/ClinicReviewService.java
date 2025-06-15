package org.example.clinic.service;

import org.example.clinic.dto.request.ClinicReviewRequest;
import org.example.clinic.dto.response.ClinicReviewResponse;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClinicReviewService {

    private final ClinicReviewRepository clinicReviewRepository;
    private final ClinicRepository clinicRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ClinicReviewService(ClinicReviewRepository clinicReviewRepository,
                               ClinicRepository clinicRepository,
                               ReviewRepository reviewRepository,
                               UserRepository userRepository) {
        this.clinicReviewRepository = clinicReviewRepository;
        this.clinicRepository = clinicRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public ClinicReviewResponse createClinicReview(ClinicReviewRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Clinic clinic = clinicRepository.findById(request.getClinicId())
                .orElseThrow(() -> new RuntimeException("Clinic not found"));

        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(() -> new RuntimeException("Review not found"));

        // Optionally, check if the user is authorized to create review here

        ClinicReview clinicReview = new ClinicReview();
        clinicReview.setClinic(clinic);
        clinicReview.setReview(review);

        ClinicReview saved = clinicReviewRepository.save(clinicReview);
        return mapToResponse(saved);
    }

    public Optional<ClinicReviewResponse> getClinicReviewById(Integer id) {
        return clinicReviewRepository.findById(id).map(this::mapToResponse);
    }

    public List<ClinicReviewResponse> getAllClinicReviews() {
        return clinicReviewRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ClinicReviewResponse updateClinicReview(Integer id, ClinicReviewRequest request, String username) {
        ClinicReview clinicReview = clinicReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClinicReview not found"));

        // Optional: check if user owns this review or has permission

        Clinic clinic = clinicRepository.findById(request.getClinicId())
                .orElseThrow(() -> new RuntimeException("Clinic not found"));

        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(() -> new RuntimeException("Review not found"));

        clinicReview.setClinic(clinic);
        clinicReview.setReview(review);

        ClinicReview updated = clinicReviewRepository.save(clinicReview);
        return mapToResponse(updated);
    }

    public void deleteClinicReview(Integer id) {
        if (!clinicReviewRepository.existsById(id)) {
            throw new RuntimeException("ClinicReview not found");
        }
        clinicReviewRepository.deleteById(id);
    }

    // Simple mapper from entity to response DTO
    private ClinicReviewResponse mapToResponse(ClinicReview entity) {
        ClinicReviewResponse response = new ClinicReviewResponse();
        response.setId(entity.getId());
        response.setClinicId(entity.getClinic().getId());
        response.setReviewId(entity.getReview().getId());
        // add more fields as needed from Review or Clinic
        return response;
    }
}
