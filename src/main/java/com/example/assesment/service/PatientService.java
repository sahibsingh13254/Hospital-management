package com.example.assesment.service;

import com.example.assesment.bean.PatientBean;

import java.util.List;

public  interface PatientService {

    PatientBean savePatient(PatientBean patientBean);
    List<PatientBean> getAllPatients();
    PatientBean getPatientById (Long patientId);

}
