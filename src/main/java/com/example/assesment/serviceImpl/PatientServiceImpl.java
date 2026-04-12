package com.example.assesment.serviceImpl;

import com.example.assesment.bean.PatientBean;
import com.example.assesment.dao.PatientDAO;
import com.example.assesment.dao.PatientDAOWrapper;
import com.example.assesment.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientDAOWrapper patientDAOWrapper;


    @Override
    public List<PatientBean> getAllPatients(){
        return patientDAOWrapper.getAllPatients();
    }


    @Override
    public PatientBean savePatient(PatientBean patientBean){
        return patientDAOWrapper.savePatient(patientBean);
    }

    /**
     To-Do Item 1.11: This method should fetch patient details by ID.

     TODO:
     --Invoke getById() method of PatientDAOWrapper to fetch patient details.
     --Return the PatientBean received from DAO layer
     */

    @Override
    public PatientBean getById(Long patientId) {
        return patientDAOWrapper.getById(patientId);
    }


}
