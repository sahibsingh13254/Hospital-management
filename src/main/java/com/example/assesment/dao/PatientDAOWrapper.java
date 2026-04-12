package com.example.assesment.dao;

import com.example.assesment.bean.PatientBean;
import com.example.assesment.entity.PatientEntity;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class PatientDAOWrapper {

    private final PatientDAO patientDAO;

    public PatientBean savePatient(PatientBean patientBean)
    {
        if(patientBean == null) {
            throw new RuntimeException("Patient Data can't be null!");
        }
        PatientEntity pEntity = convertPatientBeanToEntity(patientBean); // we are changing DTO -> Entity, because we have to save in Db, and db takes entity!

        // we are not directly using patientDAO.save(entity);, because we have to change this to DTO afterwards, we will lose it!!
        PatientEntity savedPatient = patientDAO.save(pEntity); // So we are using savedPatient,It will hold the details of patient!

        PatientBean pBean = convertPatientEntityToBean(savedPatient);// Always use the returned entity from save(),i.e savedPatient
        return pBean;
    }

    public List<PatientBean> getAllPatients(){

        List<PatientEntity> entities = patientDAO.findAll();

        // we are returning ArrayList because findAll(), empty list → if no data
        if( entities == null|| entities.isEmpty()) { return new ArrayList<>(); }

        List<PatientBean> pBeans = new ArrayList<>(); //Creates an empty list to store results
        for (PatientEntity entity : entities) { // Iterates over each PatientEntity from DB
            pBeans.add(convertPatientEntityToBean(entity)); //pBeans is the list declared in first line .add is converting into list
            // we are retutrning list only declared in first line
        }
        return pBeans;
    }

    public PatientEntity getPatientById(Long id){
        Optional<PatientEntity> optional = patientDAO.findById(id);
        return optional.orElse(null);
    }

    public PatientBean getById(Long id){
        Optional<PatientEntity> opt = patientDAO.findById(id);
        if(opt.isPresent()) {
            PatientBean bean = new PatientBean();
            BeanUtils.copyProperties(opt.get(), bean);
            return bean;
        }
        return null;
    }

        //We are taking input perimeter as patientEntity bcz that we have to convert.
    private PatientBean convertPatientEntityToBean(PatientEntity patientE){
        // We are returning DTO so the return type is PatientBean
        PatientBean patientB = new PatientBean();
        BeanUtils.copyProperties(patientE, patientB);
        return patientB;
    }

    private PatientEntity convertPatientBeanToEntity(PatientBean patientB){
        PatientEntity patientE = new PatientEntity();
        BeanUtils.copyProperties(patientB , patientE);
        return patientE;
    }


}
