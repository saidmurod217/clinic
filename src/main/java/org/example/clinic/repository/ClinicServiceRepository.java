package org.example.clinic.repository;

import org.example.clinic.entity.ClinicService;
//import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.clinic.entity.Service;


import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicServiceRepository extends JpaRepository<ClinicService, Integer> {
    List<ClinicService> findByClinicId(Integer clinicId);
    List<ClinicService> findByServiceId(Integer serviceId);
    ClinicService findByClinicIdAndServiceId(Integer clinicId, Integer serviceId);
//    Page<ClinicService> findByClinicId(Integer clinicId, Pageable pageable);
    void deleteByClinicIdAndServiceId(Integer clinicId, Integer serviceId);
    ClinicService findByService(Service service);
}