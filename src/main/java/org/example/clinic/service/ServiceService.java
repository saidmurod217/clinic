package org.example.clinic.service;

import lombok.RequiredArgsConstructor;
import org.example.clinic.dto.request.ServiceRequest;
import org.example.clinic.dto.response.ServiceResponse;
import org.example.clinic.entity.*;
import org.example.clinic.entity.ClinicService;
import org.example.clinic.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final ClinicRepository clinicRepository;
    private final ClinicServiceRepository clinicServiceRepository;
    private final UserRepository userRepository;

    public ServiceResponse createService(ServiceRequest request) {
        // Get current username from security context
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Clinic clinic = clinicRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Clinic not found"));

        Service service = new Service();
        service.setName(request.getName());
        Service savedService = serviceRepository.save(service);

        org.example.clinic.entity.ClinicService clinicService = new org.example.clinic.entity.ClinicService();
        clinicService.setClinic(clinic);
        clinicService.setService(savedService);
        clinicService.setCost(request.getCost());
        clinicService.setInfo(request.getInfo());
        clinicServiceRepository.save(clinicService);

        return toResponse(savedService, clinicService);
    }

    public List<ServiceResponse> getServicesByClinic() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Clinic clinic = clinicRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Clinic not found"));

        return clinic.getClinicServices().stream()
                .map(cs -> toResponse(cs.getService(), cs))
                .toList();
    }

    private ServiceResponse toResponse(Service service, org.example.clinic.entity.ClinicService clinicService) {
        ServiceResponse response = new ServiceResponse();
        response.setId(service.getId());
        response.setName(service.getName());
        response.setCost(clinicService.getCost());
        response.setInfo(clinicService.getInfo());
        response.setClinicId(clinicService.getClinic().getId());
        return response;
    }
    public ServiceResponse updateService(Integer serviceId, ServiceRequest request) {
        // Get authenticated user and their clinic
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        Clinic clinic = clinicRepository.findByUserId(user.getId()).orElseThrow();

        // Find ClinicService relation
        ClinicService clinicService = clinicServiceRepository
                .findByClinicIdAndServiceId(clinic.getId(), serviceId);

        // Update data
        Service service = clinicService.getService();
        service.setName(request.getName());
        serviceRepository.save(service);

        clinicService.setCost(request.getCost());
        clinicService.setInfo(request.getInfo());
        clinicServiceRepository.save(clinicService);

        return toResponse(service, clinicService);
    }

    public void deleteService(Integer serviceId) {
        // Get authenticated user and their clinic
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        Clinic clinic = clinicRepository.findByUserId(user.getId()).orElseThrow();

        // Find ClinicService relation
        org.example.clinic.entity.ClinicService clinicService = clinicServiceRepository
                .findByClinicIdAndServiceId(clinic.getId(), serviceId);
        // Delete clinic-service association and the service itself
        clinicServiceRepository.delete(clinicService);
        serviceRepository.delete(clinicService.getService());
    }

}
