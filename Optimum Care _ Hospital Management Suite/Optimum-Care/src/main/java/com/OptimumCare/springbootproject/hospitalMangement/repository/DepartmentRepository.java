package com.OptimumCare.springbootproject.hospitalMangement.repository;

import com.OptimumCare.springbootproject.hospitalMangement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
