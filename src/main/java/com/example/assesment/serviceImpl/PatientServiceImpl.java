package com.example.assesment.serviceImpl;

import com.example.assesment.bean.PatientDTO;
import com.example.assesment.dao.PatientDAOWrapper;
import com.example.assesment.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientDAOWrapper patientDAOWrapper;

    /**
     To-Do Item 1.9: This method should fetch all patient details.

     TODO:
     --Invoke getAllPatients() method of PatientDAOWrapper to fetch all patients
     --Return the list of PatientBean objects
     */

    @Override
    public List<PatientDTO> getAllPatients(){
        return patientDAOWrapper.getAllPatients();
    }

    /**
     To-Do Item 1.10: This method should save patient details.
     TODO:
     --Invoke savePatient() method of PatientDAOWrapper to save patient
     --Return the saved PatientBean received from DAO layer
     */
    @Override
    public PatientDTO savePatient(PatientDTO patientDTO){
        return patientDAOWrapper.savePatient(patientDTO);
    }

    /**
     To-Do Item 1.11: This method should fetch patient details by ID.

     TODO:
     --Invoke getById() method of PatientDAOWrapper to fetch patient details.
     --Return the PatientBean received from DAO layer
     */

    @Override
    public PatientDTO getById(Long id){
        return patientDAOWrapper.getById(id);
    }
}
