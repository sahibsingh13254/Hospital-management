package com.example.assesment.controller;


import com.example.assesment.bean.AppointmentDTO;
import com.example.assesment.bean.PatientDTO;
import com.example.assesment.service.AppointmentService;
import com.example.assesment.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController
{

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @RequestMapping(value = "/save" , method = RequestMethod.POST)
    public ResponseEntity<?> saveAppointment (@RequestBody@Valid AppointmentDTO appointmentDTO, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(result.getAllErrors());
        }
        Long patientId = appointmentDTO.getPatient().getPatientId();
        PatientDTO patient = patientService.getById(patientId);

        appointmentDTO.setPatient(patient);

        AppointmentDTO saved = appointmentService.saveAppointment(appointmentDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ResponseEntity<List<AppointmentDTO>> listAppointments(){
            List<AppointmentDTO> appointments = appointmentService.getAllAppointments();
            return ResponseEntity.ok(appointments);
        }


    }



