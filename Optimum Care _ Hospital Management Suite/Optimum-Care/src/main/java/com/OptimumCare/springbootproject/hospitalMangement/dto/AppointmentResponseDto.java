package com.OptimumCare.springbootproject.hospitalMangement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentResponseDto {
    private Long id;

    private LocalDateTime appointmentTime;

    private String reason;
}
