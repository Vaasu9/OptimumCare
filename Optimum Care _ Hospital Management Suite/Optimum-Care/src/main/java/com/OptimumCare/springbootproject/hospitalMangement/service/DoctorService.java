package com.OptimumCare.springbootproject.hospitalMangement.service;

import com.OptimumCare.springbootproject.hospitalMangement.dto.DoctorResponseDto;
import com.OptimumCare.springbootproject.hospitalMangement.dto.OnBoardNewDoctorRequestDto;
import com.OptimumCare.springbootproject.hospitalMangement.entity.Doctor;
import com.OptimumCare.springbootproject.hospitalMangement.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class))
                .toList();
    }

    public DoctorResponseDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found eith id: " + id));
        return modelMapper.map(doctor, DoctorResponseDto.class);
    }

    @Transactional
    public DoctorResponseDto onBoardNewDoctor(OnBoardNewDoctorRequestDto onBoardNewDoctorRequestDto) {
        Doctor doctor = Doctor.builder()
                .name(onBoardNewDoctorRequestDto.getName())
                .email(onBoardNewDoctorRequestDto.getEmail())
                .specialization(onBoardNewDoctorRequestDto.getSpecialization())
                .build();

        return modelMapper.map(doctorRepository.save(doctor), DoctorResponseDto.class);
    }
}
