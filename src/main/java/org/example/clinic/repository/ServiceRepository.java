package org.example.clinic.repository;

import org.example.clinic.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    boolean existsByName(String name);
}