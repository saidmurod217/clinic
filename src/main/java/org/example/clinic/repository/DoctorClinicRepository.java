package org.example.clinic.repository;

import org.example.clinic.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorClinicRepository extends JpaRepository<DoctorClinic, Integer> {
    Optional<Doctor> findDoctorById(Integer id);
}