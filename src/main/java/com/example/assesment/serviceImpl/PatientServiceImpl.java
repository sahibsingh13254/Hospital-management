package com.example.assesment.serviceImpl;

import com.example.assesment.bean.PatientDTO;
import com.example.assesment.entity.PatientEntity;
import com.example.assesment.repository.PatientRepository;
import com.example.assesment.service.PatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final ModelMapper modelMapper;
    private final PatientRepository patientRepository;



    @Override
    public PatientDTO savePatient(PatientDTO patientDTO)
    {
        if (patientDTO == null) {
            throw new RuntimeException("Patient Data can't be null!");
        }

        // DTO → Entity
        PatientEntity entity = modelMapper.map(patientDTO, PatientEntity.class);

        // Save
        PatientEntity savedPatient = patientRepository.save(entity);

        // Entity → DTO
        return modelMapper.map(savedPatient, PatientDTO.class);
    }

    @Override
    public List<PatientDTO> getAllPatients(){

        List<PatientEntity> entities = patientRepository.findAll();

        if (entities == null || entities.isEmpty()) {
            return new ArrayList<>();
        }

        return entities.stream()
                .map(entity -> modelMapper.map(entity, PatientDTO.class))
                .toList();
    }

    public PatientDTO getById(Long patientId){

        PatientEntity patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient Not Found at id:" +patientId));

                return modelMapper.map(patient , PatientDTO.class);



    }
}
