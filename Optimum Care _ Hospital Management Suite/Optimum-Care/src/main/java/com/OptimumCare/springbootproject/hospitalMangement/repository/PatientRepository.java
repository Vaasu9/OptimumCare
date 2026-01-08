package com.OptimumCare.springbootproject.hospitalMangement.repository;

import com.OptimumCare.springbootproject.hospitalMangement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
