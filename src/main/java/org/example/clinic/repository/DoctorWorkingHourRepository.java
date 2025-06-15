package org.example.clinic.repository;

import org.example.clinic.entity.DoctorWorkingHour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorWorkingHourRepository extends JpaRepository<DoctorWorkingHour, Integer> {
}