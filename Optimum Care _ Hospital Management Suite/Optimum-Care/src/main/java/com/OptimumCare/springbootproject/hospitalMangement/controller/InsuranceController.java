package com.OptimumCare.springbootproject.hospitalMangement.controller;

import com.OptimumCare.springbootproject.hospitalMangement.dto.InsuranceResponseDto;
import com.OptimumCare.springbootproject.hospitalMangement.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

    @GetMapping("/insuranceDetails")
    public ResponseEntity<List<InsuranceResponseDto>> getAllInsuranceDetails() {
        return ResponseEntity.ok(insuranceService.getAllInsuranceDetails());
    }

    @PatchMapping("/updateInsurance/{insuranceId}")
    public ResponseEntity<InsuranceResponseDto> updateInsuranceDetails(@PathVariable Long insuranceId,
                                                                       @RequestBody Map<String, Object> updateInsurance) {
        return ResponseEntity.ok(insuranceService.updateInsuranceDetails(insuranceId, updateInsurance));
    }
}

