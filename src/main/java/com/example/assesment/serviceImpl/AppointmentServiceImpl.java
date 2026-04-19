package com.example.assesment.serviceImpl;

import com.example.assesment.bean.AppointmentDTO;
import com.example.assesment.entity.AppointmentEntity;
import com.example.assesment.entity.PatientEntity;
import com.example.assesment.repository.AppointmentRepository;
import com.example.assesment.repository.PatientRepository;
import com.example.assesment.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Override
    public AppointmentDTO saveAppointment(AppointmentDTO aBean){

        // extract patientId
        Long patientId = aBean.getPatient().getPatientId();

        PatientEntity patientEntity = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("User not found with id:" + patientId));

        // DTO → Entity
        AppointmentEntity aEntity = modelMapper.map(aBean, AppointmentEntity.class);

        // override patient (VERY IMPORTANT)
        aEntity.setPatient(patientEntity);

        AppointmentEntity saved = appointmentRepository.save(aEntity);

        // Entity → DTO
        return modelMapper.map(saved, AppointmentDTO.class);
    }

    @Override
    public List<AppointmentDTO> getAllAppointments(){

        List<AppointmentEntity> entities = appointmentRepository.findAll();

        if (entities == null) {
            return new ArrayList<>();
        }

        return entities.stream()
                .map(entity -> modelMapper.map(entity, AppointmentDTO.class))
                .toList();
    }
}

