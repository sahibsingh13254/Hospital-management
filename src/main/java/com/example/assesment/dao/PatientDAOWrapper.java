package com.example.assesment.dao;

import com.example.assesment.bean.PatientDTO;
import com.example.assesment.entity.PatientEntity;
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

    public PatientDTO savePatient(PatientDTO patientDTO)
    {
        if(patientDTO == null) {
            throw new RuntimeException("Patient Data can't be null!");
        }
        PatientEntity pEntity = convertPatientBeanToEntity(patientDTO); // we are changing DTO -> Entity, because we have to save in Db, and db takes entity!

        // we are not directly using patientDAO.save(entity);, because we have to change this to DTO afterwards, we will lose it!!
        PatientEntity savedPatient = patientDAO.save(pEntity); // So we are using savedPatient,It will hold the details of patient!

        PatientDTO pBean = convertPatientEntityToBean(savedPatient);// Always use the returned entity from save(),i.e savedPatient
        return pBean;
    }

    public List<PatientDTO> getAllPatients(){

        List<PatientEntity> entities = patientDAO.findAll();

        // we are returning ArrayList because findAll(), empty list → if no data
        if( entities == null|| entities.isEmpty()) { return new ArrayList<>(); }

        List<PatientDTO> pBeans = new ArrayList<>(); //Creates an empty list to store results
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

    public PatientDTO getById(Long id){
        Optional<PatientEntity> opt = patientDAO.findById(id);
        if(opt.isPresent()) {
            PatientDTO bean = new PatientDTO();
            BeanUtils.copyProperties(opt.get(), bean);
            return bean;
        }
        return null;
    }

        //We are taking input perimeter as patientEntity bcz that we have to convert.
    private PatientDTO convertPatientEntityToBean(PatientEntity patientE){
        // We are returning DTO so the return type is PatientBean
        PatientDTO patientB = new PatientDTO();
        BeanUtils.copyProperties(patientE, patientB);
        return patientB;
    }

    private PatientEntity convertPatientBeanToEntity(PatientDTO patientB){
        PatientEntity patientE = new PatientEntity();
        BeanUtils.copyProperties(patientB , patientE);
        return patientE;
    }


}
