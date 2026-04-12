package com.example.assesment.serviceImpl;


import com.example.assesment.bean.AppointmentBean;
import com.example.assesment.dao.AppointmentDAOWrapper;
import com.example.assesment.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentDAOWrapper appointmentDAOWrapper;

    @Override
    public AppointmentBean saveAppointment(AppointmentBean appointmentBean)
    {
        return appointmentDAOWrapper.saveAppointment(appointmentBean);
    }

    @Override
    public List<AppointmentBean> getAllAppointments(){
        return appointmentDAOWrapper.getAllAppointments();
    }

}