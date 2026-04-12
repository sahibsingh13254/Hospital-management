package com.example.assesment.dao;


import com.example.assesment.bean.AppointmentBean;
import com.example.assesment.bean.PatientBean;
import com.example.assesment.entity.AppointmentEntity;
import com.example.assesment.entity.PatientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AppointmentDAOWrapper {

    private final AppointmentDAO appointmentDAO;
    private final PatientDAO patientDAO;   // needed for ManyToOne!



    /**
     * To-Do Item 1.7: This method should add new appointment details to the
     database. NOTE: This requirement needs to be implemented using Spring Data
     JPA.

     * TODO:
     --Fetch patient ID from incoming AppointmentBean
     --Invoke Method findById()of PatientDAO to fetch patient details from database.
     If patient not found, throw exception with proper message
     --Convert AppointmentBean to AppointmentEntity(Hint: Utilize provided utility methods)
     --Set patient to appointment entity (Many-to-One relationship)
     --Invoke save method of AppointmentDAO to store appointment details
     --Convert saved AppointmentEntity back to AppointmentBean(Hint: Utilize provided utility methods)
     --Return the saved appointment details
     */

    // aBean - an object (instance) of AppointmentBean, containing data sent from client
    public AppointmentBean saveAppointment(AppointmentBean aBean){
        //get patientId from bean
        Long patientId = aBean.getPatient().getPatientId();

        PatientEntity patientEntity = patientDAO.findById(patientId)
                .orElseThrow(() -> new RuntimeException("User not found with id:" + patientId));

        AppointmentEntity aEntity = convertAppointmentBeanToEntity(aBean);

        //set patient (ManyToOne relationship!)
        aEntity.setPatient(patientEntity);

        // Doing like this because this is entity and we are storting in saved to change in DTO, otherwise we will loose
        AppointmentEntity saved = appointmentDAO.save(aEntity);
         return convertAppointmentEntityToBean(saved);
    }



    /**
     To-Do Item 1.8: This method should fetch all appointment details from the database.
     NOTE: This requirement needs to be implemented using Spring Data JPA.

     TODO:
     --Invoke findAll() method of AppointmentDAO to get all appointment entities
     --Check if the returned list is empty, handle appropriately
     --Convert each AppointmentEntity to AppointmentBean (Hint: Utilize provided utility methods)
     --Store all converted beans into a list
     --Handle null values safely during conversion
     --Return the list of AppointmentBean objects
     */

    public List<AppointmentBean> getAllAppointments(){

        // fetch from DB
        List<AppointmentEntity> entities  = appointmentDAO.findAll();

        if (entities == null) { return new ArrayList<>(); }

        List<AppointmentBean> beans = new ArrayList<>();

        for (AppointmentEntity entity : entities)
        {
            beans.add(convertAppointmentEntityToBean(entity));
        }
        return beans;
    }





       // Entity -> Bean
    private AppointmentBean convertAppointmentEntityToBean(AppointmentEntity aEntity){
        AppointmentBean aBean = new AppointmentBean();
        BeanUtils.copyProperties(aEntity , aBean);
        // patient         ❌ SKIPPED (different types!)
        //manually handle patient
        if(aEntity.getPatient() != null){
            PatientBean patientBean = new PatientBean();
            BeanUtils.copyProperties(aEntity.getPatient(), patientBean);
            aBean.setPatient(patientBean);
        }
        return aBean;
    }

    //Bean -> Entity
    private AppointmentEntity convertAppointmentBeanToEntity(AppointmentBean aBean){
        AppointmentEntity aEntity = new AppointmentEntity();
        BeanUtils.copyProperties(aBean , aEntity);
        return aEntity;
    }



}
