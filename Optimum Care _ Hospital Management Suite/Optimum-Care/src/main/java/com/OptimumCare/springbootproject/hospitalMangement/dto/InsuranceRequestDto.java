package com.OptimumCare.springbootproject.hospitalMangement.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class InsuranceRequestDto {
    private String policyNumber;

    private String provider;

    private LocalDate validUntil;
}
