package org.example.clinic.repository;

import org.example.clinic.entity.WorkingHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkingHourRepository extends JpaRepository<WorkingHour, Integer> {
    List<WorkingHour> findByClinicId(Integer clinicId);
    void deleteByClinicId(Integer clinicId);
}