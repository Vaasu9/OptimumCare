package com.OptimumCare.springbootproject.hospitalMangement.dto;

import lombok.Data;

@Data
public class OnBoardNewDoctorRequestDto {
    private String name;

    private String email;

    private String Specialization;
}
