package com.OptimumCare.springbootproject.hospitalMangement.service;

import com.OptimumCare.springbootproject.hospitalMangement.dto.AppointmentResponseDto;
import com.OptimumCare.springbootproject.hospitalMangement.dto.AppointmentRequestDto;
import com.OptimumCare.springbootproject.hospitalMangement.entity.Appointment;
import com.OptimumCare.springbootproject.hospitalMangement.entity.Doctor;
import com.OptimumCare.springbootproject.hospitalMangement.entity.Patient;
import com.OptimumCare.springbootproject.hospitalMangement.repository.AppointmentRepository;
import com.OptimumCare.springbootproject.hospitalMangement.repository.DoctorRepository;
import com.OptimumCare.springbootproject.hospitalMangement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public AppointmentResponseDto createNewAppointment(AppointmentRequestDto appointmentRequestDto) {
        Long doctorId = appointmentRequestDto.getDoctorId();
        Long patientId = appointmentRequestDto.getPatientId();

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        Doctor doctor =  doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorId));

        Appointment appointment = Appointment.builder()
                .reason(appointmentRequestDto.getReason())
                .appointmentTime(appointmentRequestDto.getAppointmentTime())
                .build();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        patient.getAppointments().add(appointment);

        appointment = appointmentRepository.save(appointment);
        return modelMapper.map(appointment, AppointmentResponseDto.class);
    }

    @Transactional
    public AppointmentResponseDto reAssignAppointmentToDoctor(Long doctorId, Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("No appointment exists with id: " + appointmentId));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorId));

        appointment.setDoctor(doctor);
        doctor.getAppointments().add(appointment);

        return modelMapper.map(appointment, AppointmentResponseDto.class);
    }

    public List<AppointmentResponseDto> getAllAppointmentsOfDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("No Doctor found with id: " + doctorId));

        return doctor.getAppointments()
                .stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentResponseDto.class))
                .toList();
    }
}
