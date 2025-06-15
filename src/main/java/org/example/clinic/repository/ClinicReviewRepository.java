package org.example.clinic.repository;

import org.example.clinic.entity.ClinicReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicReviewRepository extends JpaRepository<ClinicReview, Integer> {
    // Optional: Add custom query methods if needed
//    List<ClinicReview> findByClinicId(Long clinicId);
}
