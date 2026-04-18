package com.example.assesment.service;

import com.example.assesment.bean.PatientDTO;

import java.util.List;

public  interface PatientService {

    PatientDTO savePatient(PatientDTO patientDTO);
    List<PatientDTO> getAllPatients();
    PatientDTO getById (Long patientId);

}
