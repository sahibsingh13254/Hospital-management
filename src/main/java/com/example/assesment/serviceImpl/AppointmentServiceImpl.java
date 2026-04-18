package com.example.assesment.serviceImpl;


import com.example.assesment.bean.AppointmentDTO;
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
    public AppointmentDTO saveAppointment(AppointmentDTO appointmentDTO)
    {
        return appointmentDAOWrapper.saveAppointment(appointmentDTO);
    }

    @Override
    public List<AppointmentDTO> getAllAppointments(){
        return appointmentDAOWrapper.getAllAppointments();
    }

}