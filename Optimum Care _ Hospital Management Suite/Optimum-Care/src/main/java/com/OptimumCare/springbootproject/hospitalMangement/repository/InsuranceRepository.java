package com.OptimumCare.springbootproject.hospitalMangement.repository;

import com.OptimumCare.springbootproject.hospitalMangement.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
