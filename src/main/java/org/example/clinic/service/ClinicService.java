// --- ClinicService.java ---
package org.example.clinic.service;

import lombok.RequiredArgsConstructor;
import org.example.clinic.dto.request.ClinicRequest;
import org.example.clinic.dto.response.ClinicResponse;
import org.example.clinic.entity.*;
import org.example.clinic.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClinicService {

    private final ClinicRepository clinicRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorClinicRepository doctorClinicRepository;
    private final WorkingHourRepository workingHourRepository;
    private final DoctorWorkingHourRepository doctorWorkingHourRepository;

    public ClinicResponse createClinic(ClinicRequest request, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Clinic clinic = new Clinic();
        clinic.setUser(user);
        clinic.setName(request.getName());
        clinic.setAbout(request.getAbout());
        clinic.setCertification(request.getCertification());
        clinic.setAddress(request.getAddress());
        clinic.setPhone(request.getPhone());
        clinic.setWeblink(request.getWeblink());
        clinic.setSince(request.getSince());
        clinic.setRating(0F);
        return toResponse(clinicRepository.save(clinic));
    }

    public ClinicResponse getClinic(Integer id) {
        return toResponse(clinicRepository.findById(id).orElseThrow());
    }

    public List<ClinicResponse> getAllClinics() {
        return clinicRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ClinicResponse updateClinic(Integer id, ClinicRequest request) {
        Clinic clinic = clinicRepository.findById(id).orElseThrow();
        clinic.setName(request.getName());
        clinic.setAbout(request.getAbout());
        clinic.setCertification(request.getCertification());
        clinic.setAddress(request.getAddress());
        clinic.setPhone(request.getPhone());
        clinic.setWeblink(request.getWeblink());
        clinic.setSince(request.getSince());
        return toResponse(clinicRepository.save(clinic));
    }

    public void deleteClinic(Integer id) {
        clinicRepository.deleteById(id);
    }

    public void assignDoctor(Integer clinicId, Integer doctorId) {
        Clinic clinic = clinicRepository.findById(clinicId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        DoctorClinic doctorClinic = new DoctorClinic();
        doctorClinic.setClinic(clinic);
        doctorClinic.setDoctor(doctor);
        doctorClinicRepository.save(doctorClinic);
    }

    public void addWorkingHour(Integer clinicId, WorkingHour workingHour) {
        Clinic clinic = clinicRepository.findById(clinicId).orElseThrow();
        workingHour.setClinic(clinic);
        workingHourRepository.save(workingHour);
    }

    public void assignToWorkingHour(Integer doctorId, Integer workingHourId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        WorkingHour wh = workingHourRepository.findById(workingHourId).orElseThrow();
        DoctorWorkingHour dwh = new DoctorWorkingHour();
        dwh.setDoctor(doctor);
        dwh.setWorkingHours(wh);
        doctorWorkingHourRepository.save(dwh);
    }

    private ClinicResponse toResponse(Clinic clinic) {
        ClinicResponse response = new ClinicResponse();
        response.setId(clinic.getId());
        response.setName(clinic.getName());
        response.setAbout(clinic.getAbout());
        response.setCertification(clinic.getCertification());
        response.setAddress(clinic.getAddress());
        response.setPhone(clinic.getPhone());
        response.setWeblink(clinic.getWeblink());
        response.setSince(clinic.getSince());
        response.setRating(clinic.getRating());
        response.setUsername(clinic.getUser().getUsername());
        return response;
    }
}
