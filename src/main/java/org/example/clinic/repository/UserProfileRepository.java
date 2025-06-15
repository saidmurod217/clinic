package org.example.clinic.repository;

import org.example.clinic.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    Optional<UserProfile> findByUserId(Integer userId);
    List<UserProfile> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name, String surname);
    List<UserProfile> findByAgeBetween(Integer minAge, Integer maxAge);
}