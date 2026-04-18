package com.example.assesment.ServiceTest;

import com.example.assesment.AssesmentApplication;
import com.example.assesment.bean.AppointmentDTO;
import com.example.assesment.bean.PatientDTO;
import com.example.assesment.serviceImpl.AppointmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AssesmentApplication.class)
@Transactional
public class AppointmentServiceTest {

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Test
    public void testSaveAppointment() {
        PatientDTO pBean = new PatientDTO();
        pBean.setPatientId(1L);

        AppointmentDTO aBean = new AppointmentDTO();
        aBean.setAppointmentDate(LocalDate.of(2026, 4, 13));
        aBean.setAppointmentTime(LocalTime.of(10, 30));
        aBean.setDoctorName("Dr. Smith");
        aBean.setPatient(pBean);  // link patient!

        //ACT
        AppointmentDTO saved = appointmentService.saveAppointment(aBean);
        Assertions.assertNotNull(saved);

    }

    @Test
    public void getAllAppointments(){
        List<AppointmentDTO> aBean = appointmentService.getAllAppointments();

        Assertions.assertNotNull(aBean);

    }

}

