package org.example.clinic.service;

import lombok.RequiredArgsConstructor;
import org.example.clinic.dto.request.WorkingHourRequest;
import org.example.clinic.dto.response.WorkingHourResponse;
import org.example.clinic.entity.Clinic;
import org.example.clinic.entity.Doctor;
import org.example.clinic.entity.DoctorWorkingHour;
import org.example.clinic.entity.WorkingHour;
import org.example.clinic.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkingHourService {

    private final WorkingHourRepository workingHourRepository;
    private final ClinicRepository clinicRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorWorkingHourRepository doctorWorkingHourRepository;

    public WorkingHourResponse createWorkingHour(Integer clinicId, WorkingHourRequest request) {
        Clinic clinic = clinicRepository.findById(clinicId).orElseThrow();
        WorkingHour wh = new WorkingHour();
        wh.setClinic(clinic);
        wh.setFromTime(request.getFromTime());
        wh.setToTime(request.getToTime());
        return toResponse(workingHourRepository.save(wh));
    }

    public List<WorkingHourResponse> getAllWorkingHours() {
        return workingHourRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public WorkingHourResponse getWorkingHour(Integer id) {
        WorkingHour wh = workingHourRepository.findById(id).orElseThrow();
        return toResponse(wh);
    }

    public WorkingHourResponse updateWorkingHour(Integer id, WorkingHourRequest request) {
        WorkingHour wh = workingHourRepository.findById(id).orElseThrow();
        wh.setFromTime(request.getFromTime());
        wh.setToTime(request.getToTime());
        return toResponse(workingHourRepository.save(wh));
    }

    public void deleteWorkingHour(Integer id) {
        workingHourRepository.deleteById(id);
    }

    public void assignDoctorToWorkingHour(Integer doctorId, Integer workingHourId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        WorkingHour wh = workingHourRepository.findById(workingHourId).orElseThrow();
        DoctorWorkingHour dwh = new DoctorWorkingHour();
        dwh.setDoctor(doctor);
        dwh.setWorkingHours(wh);
        doctorWorkingHourRepository.save(dwh);
    }

    private WorkingHourResponse toResponse(WorkingHour wh) {
        WorkingHourResponse dto = new WorkingHourResponse();
        dto.setId(wh.getId());
        dto.setFromTime(wh.getFromTime());
        dto.setToTime(wh.getToTime());
        dto.setClinicId(wh.getClinic().getId());
        return dto;
    }
}
