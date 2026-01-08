package com.OptimumCare.springbootproject.hospitalMangement.dto;

import com.OptimumCare.springbootproject.hospitalMangement.entity.type.BloodGroupType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PatientResponseDto {
    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private String email;

    private String gender;

    private BloodGroupType bloodGroup;

    private LocalDateTime createdAt;
}
