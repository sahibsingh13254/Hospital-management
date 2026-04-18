package com.example.assesment.service;

import com.example.assesment.bean.AppointmentDTO;

import java.util.List;

public interface AppointmentService {

    AppointmentDTO saveAppointment(AppointmentDTO appointmentDTO);
    List<AppointmentDTO> getAllAppointments();

}
