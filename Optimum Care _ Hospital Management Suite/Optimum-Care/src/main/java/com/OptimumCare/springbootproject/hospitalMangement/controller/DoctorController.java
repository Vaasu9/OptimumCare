package com.OptimumCare.springbootproject.hospitalMangement.controller;

import com.OptimumCare.springbootproject.hospitalMangement.dto.AppointmentResponseDto;
import com.OptimumCare.springbootproject.hospitalMangement.dto.DoctorResponseDto;
import com.OptimumCare.springbootproject.hospitalMangement.dto.OnBoardNewDoctorRequestDto;
import com.OptimumCare.springbootproject.hospitalMangement.repository.PatientRepository;
import com.OptimumCare.springbootproject.hospitalMangement.service.AppointmentService;
import com.OptimumCare.springbootproject.hospitalMangement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @GetMapping("/appointments/{doctorId}")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsOfDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getAllAppointmentsOfDoctor(doctorId));
    }

    @PostMapping("/doctors/onBoard")
    public ResponseEntity<DoctorResponseDto> onBoardNewDoctor(@RequestBody OnBoardNewDoctorRequestDto onBoardNewDoctorRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(doctorService.onBoardNewDoctor(onBoardNewDoctorRequestDto));
    }

    @PatchMapping("/doctors/{doctorId}/appointments/{appointmentId}")
    public ResponseEntity<AppointmentResponseDto> reAssignAppointmentToDoctor(@PathVariable Long doctorId,
                                                                              @PathVariable Long appointmentId) {
        return ResponseEntity.ok(appointmentService.reAssignAppointmentToDoctor(doctorId, appointmentId));
    }

}
