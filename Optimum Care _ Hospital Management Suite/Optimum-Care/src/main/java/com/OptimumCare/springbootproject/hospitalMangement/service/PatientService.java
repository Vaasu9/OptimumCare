package com.OptimumCare.springbootproject.hospitalMangement.service;

import com.OptimumCare.springbootproject.hospitalMangement.dto.PatientResponseDto;
import com.OptimumCare.springbootproject.hospitalMangement.entity.Patient;
import com.OptimumCare.springbootproject.hospitalMangement.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public List<PatientResponseDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patient -> modelMapper.map(patient, PatientResponseDto.class))
                .toList();
    }

    public PatientResponseDto getPatientById(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + patientId));
        return modelMapper.map(patient, PatientResponseDto.class);
    }
}
