package com.OptimumCare.springbootproject.hospitalMangement.service;

import com.OptimumCare.springbootproject.hospitalMangement.dto.InsuranceRequestDto;
import com.OptimumCare.springbootproject.hospitalMangement.dto.InsuranceResponseDto;
import com.OptimumCare.springbootproject.hospitalMangement.dto.PatientResponseDto;
import com.OptimumCare.springbootproject.hospitalMangement.entity.Insurance;
import com.OptimumCare.springbootproject.hospitalMangement.entity.Patient;
import com.OptimumCare.springbootproject.hospitalMangement.repository.InsuranceRepository;
import com.OptimumCare.springbootproject.hospitalMangement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public List<InsuranceResponseDto> getAllInsuranceDetails() {
        return insuranceRepository.findAll()
                .stream()
                .map(insurance -> modelMapper.map(insurance, InsuranceResponseDto.class))
                .toList();
    }

    @Transactional
    public PatientResponseDto assignInsuranceToPatient(Long patientId, Long insuranceId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        Insurance insurance = insuranceRepository.findById(insuranceId)
                .orElseThrow(() -> new EntityNotFoundException("Insurance policy not found with id: " + insuranceId));

        patient.setInsurance(insurance);
        insurance.setPatient(patient);

        return modelMapper.map(patient, PatientResponseDto.class);
    }

    @Transactional
    public PatientResponseDto disassociateInsuranceFromPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        patient.setInsurance(null);
        return modelMapper.map(patient, PatientResponseDto.class);
    }

    public InsuranceResponseDto getInsuranceDetailsOfPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        return modelMapper.map(patient.getInsurance(), InsuranceResponseDto.class);
    }

    @Transactional
    public InsuranceResponseDto updateInsuranceDetails(Long insuranceId, Map<String, Object> updateInsurance) {
        Insurance insurance = insuranceRepository.findById(insuranceId)
                .orElseThrow(() -> new EntityNotFoundException("No insurance with id: "+ insuranceId));

        updateInsurance.forEach((field, value) -> {
            switch (field) {
                case "provider":
                    insurance.setProvider((String) value);
                    break;
                case "validUntil":
                    insurance.setValidUntil((LocalDate) value);
                    break;
                case "policyNumber":
                    insurance.setPolicyNumber((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field not supported");
            }
        });
        Insurance savedInsurance = insuranceRepository.save(insurance);
        return modelMapper.map(insurance, InsuranceResponseDto.class);
    }
}
