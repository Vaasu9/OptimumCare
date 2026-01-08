package com.OptimumCare.springbootproject.hospitalMangement.controller;

import com.OptimumCare.springbootproject.hospitalMangement.dto.*;
import com.OptimumCare.springbootproject.hospitalMangement.entity.Insurance;
import com.OptimumCare.springbootproject.hospitalMangement.entity.Patient;
import com.OptimumCare.springbootproject.hospitalMangement.service.AppointmentService;
import com.OptimumCare.springbootproject.hospitalMangement.service.InsuranceService;
import com.OptimumCare.springbootproject.hospitalMangement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final InsuranceService insuranceService;
    private final AppointmentService appointmentService;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping("/patients/{patientId}/insuranceDetails")
    public ResponseEntity<InsuranceResponseDto> getInsuranceDetailsOfPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(insuranceService.getInsuranceDetailsOfPatient(patientId));
    }

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appointmentService.createNewAppointment(appointmentRequestDto));
    }

    @PatchMapping("/patients/{patientId}/insurance/{insuranceId}")
    public ResponseEntity<PatientResponseDto> assignInsuranceToPatient(@PathVariable Long patientId,
                                                                       @PathVariable Long insuranceId) {
        return ResponseEntity.ok(insuranceService.assignInsuranceToPatient(patientId, insuranceId));
    }
}
