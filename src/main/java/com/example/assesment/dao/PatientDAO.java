package com.example.assesment.dao;

import com.example.assesment.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDAO extends JpaRepository<PatientEntity,Long> {
}
