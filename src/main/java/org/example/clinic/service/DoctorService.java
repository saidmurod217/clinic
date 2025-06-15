package org.example.clinic.service;

import lombok.RequiredArgsConstructor;
import org.example.clinic.dto.request.DoctorRequest;
import org.example.clinic.dto.response.DoctorResponse;
import org.example.clinic.entity.*;
import org.example.clinic.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ClinicRepository clinicRepository;
    private final WorkingHourRepository workingHourRepository;
    private final DoctorClinicRepository doctorClinicRepository;
    private final DoctorWorkingHourRepository doctorWorkingHourRepository;
    private final DoctorReviewRepository doctorReviewRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public DoctorResponse createDoctor(DoctorRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setName(request.getName());
        doctor.setSurname(request.getSurname());
        doctor.setBio(request.getBio());
        doctor.setCertificates(request.getCertificates());
        doctor.setRating(0f);

        return mapToResponse(doctorRepository.save(doctor));
    }

    public DoctorResponse getDoctor(Integer id) {
        return mapToResponse(doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found")));
    }

    public List<DoctorResponse> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public DoctorResponse updateDoctor(Integer id, DoctorRequest request) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctor.setName(request.getName());
        doctor.setSurname(request.getSurname());
        doctor.setBio(request.getBio());
        doctor.setCertificates(request.getCertificates());
        return mapToResponse(doctorRepository.save(doctor));
    }

    public void deleteDoctor(Integer id) {
        doctorRepository.deleteById(id);
    }

    public void assignToClinic(Integer doctorId, Integer clinicId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Clinic clinic = clinicRepository.findById(clinicId).orElseThrow();
        DoctorClinic dc = new DoctorClinic();
        dc.setDoctor(doctor);
        dc.setClinic(clinic);
        doctorClinicRepository.save(dc);
    }

    public void assignToWorkingHour(Integer doctorId, Integer workingHourId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        WorkingHour wh = workingHourRepository.findById(workingHourId).orElseThrow();
        DoctorWorkingHour dwh = new DoctorWorkingHour();
        dwh.setDoctor(doctor);
        dwh.setWorkingHours(wh);
        doctorWorkingHourRepository.save(dwh);
    }

    public void assignReviewToDoctor(Integer doctorId, Integer reviewId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        DoctorReview dr = new DoctorReview();
        dr.setDoctor(doctor);
        dr.setReview(review);
        doctorReviewRepository.save(dr);
    }

    private DoctorResponse mapToResponse(Doctor doctor) {
        DoctorResponse res = new DoctorResponse();
        res.setId(doctor.getId());
        res.setName(doctor.getName());
        res.setSurname(doctor.getSurname());
        res.setBio(doctor.getBio());
        res.setCertificates(doctor.getCertificates());
        res.setRating(doctor.getRating());
        res.setUsername(doctor.getUser().getUsername());
        return res;
    }
}
