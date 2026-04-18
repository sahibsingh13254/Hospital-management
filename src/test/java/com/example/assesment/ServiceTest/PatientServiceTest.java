package com.example.assesment.ServiceTest;


import com.example.assesment.AssesmentApplication;
import com.example.assesment.bean.PatientDTO;
import com.example.assesment.serviceImpl.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes =  AssesmentApplication.class)
@Transactional
public class PatientServiceTest {

    @Autowired
    private PatientServiceImpl patientService;

    @Test
    public void testSavePatient(){
        // prepare patient data
        PatientDTO bean = new PatientDTO(); // In testing bean/dto is used always

        bean.setPatientName("Raj");
        bean.setAge(23);
        bean.setGender("Male");
        bean.setPhone_Number("9876543217");


        // Act-call a save method
        PatientDTO saved = patientService.savePatient(bean);

        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getPatientId());
        Assertions.assertEquals("Raj", saved.getPatientName());

    }

    @Test
    public void toGetAllPatients(){
        // Act-call
        List<PatientDTO> list = patientService.getAllPatients();
        Assertions.assertNotNull(list);

    }

    @Test
    public void getById()
    {
        // ACT — call get by id
        // assumes patient with id=1 exists in DB!
        PatientDTO bean = patientService.getById(1L);

        Assertions.assertNotNull(bean);
    }




}
