package com.example.assesment.dao;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AppointmentDAOWrapper {

    private final AppointmentDAO appointmentDAO;


}
