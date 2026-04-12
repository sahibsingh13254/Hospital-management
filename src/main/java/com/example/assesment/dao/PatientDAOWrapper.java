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

    /**
     To-Do Item 1.3: This method should save patient details into the database.
     NOTE: This requirement needs to be implemented using Spring Data JPA.

     TODO:
     --Check if the input PatientBean is null, throw appropriate exception
     --Convert PatientBean to PatientEntity(Hint: Utilize provided utility methods)
     --Invoke save() method of PatientDAO to persist patient details
     --Convert saved PatientEntity back to PatientBean(Hint: Utilize provided utility methods)
     --Return the saved patient details
     */

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
    /**
     To-Do Item 1.4: This method should fetch all patient details from the database.
     NOTE: This requirement needs to be implemented using Spring Data JPA.

     TODO:
     --Invoke findAll() method of PatientDAO to retrieve all patient entities
     --Check if the returned list is null or empty, handle appropriately
     --Convert each PatientEntity to PatientBean (Hint: Utilize provided utility methods)
     --Add all converted beans to a result list
     --Return the list of PatientBean objects
     */

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
    /**
     To-Do Item 1.5: This method should fetch patient details by ID from the database.
     NOTE: This requirement needs to be implemented using Spring Data JPA.

     TODO:
     --Invoke findById() method of PatientDAO to retrieve patient entity.
     */

    public PatientEntity getPatientById(Long id){
        Optional<PatientEntity> optional = patientDAO.findById(id);
        return optional.orElse(null);
    }

    public PatientBean getById(Long id){
        Optional <PatientEntity> opt = patientDAO.findById(id);
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
