package com.example.assesment.controller;


import com.example.assesment.bean.PatientBean;
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
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;



    @RequestMapping(value = "/save" , method = RequestMethod.POST)
        public ResponseEntity<?> savePatient(@Valid @RequestBody PatientBean patientBean, BindingResult result) throws Exception
    {
        if(result.hasErrors())
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(result.getAllErrors());
        }
        PatientBean saved = patientService.savePatient(patientBean);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public ResponseEntity<List<PatientBean>> listPatients(){
        List<PatientBean> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());

}}
