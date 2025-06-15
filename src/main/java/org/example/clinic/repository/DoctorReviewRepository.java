package org.example.clinic.repository;

import org.example.clinic.entity.DoctorReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorReviewRepository extends JpaRepository<DoctorReview, Integer> {
}