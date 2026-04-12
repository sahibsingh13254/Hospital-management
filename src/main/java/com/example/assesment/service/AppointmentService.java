package com.example.assesment.service;

import com.example.assesment.bean.AppointmentBean;

import java.util.List;

public interface AppointmentService {

    AppointmentBean saveAppointment(AppointmentBean appointmentBean);
    List<AppointmentBean> getAllAppointments();

}
