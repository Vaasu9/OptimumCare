package com.OptimumCare.springbootproject.hospitalMangement.repository;

import com.OptimumCare.springbootproject.hospitalMangement.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
