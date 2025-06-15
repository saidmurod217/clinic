package org.example.clinic.repository;

import org.example.clinic.entity.Clinic;
import org.example.clinic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

    Optional<Clinic> findByUserId(Integer userId); // <- Change to Optional

    boolean existsByUserIdAndName(Integer userId, String name);

    List<Clinic> findByNameContainingIgnoreCase(String name);

    Clinic findByUser(User user);
}
