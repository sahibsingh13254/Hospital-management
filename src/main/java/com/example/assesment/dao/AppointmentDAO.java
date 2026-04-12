package com.example.assesment.dao;


import com.example.assesment.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentDAO extends JpaRepository<AppointmentEntity,Long> {

}
