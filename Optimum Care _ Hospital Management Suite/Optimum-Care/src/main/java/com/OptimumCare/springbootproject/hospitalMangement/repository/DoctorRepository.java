package com.OptimumCare.springbootproject.hospitalMangement.repository;

import com.OptimumCare.springbootproject.hospitalMangement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
