package com.example.assesment.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "appointments")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String doctorName;
    private String status;

    @ManyToOne // Many Appointments → One Patient
    @JoinColumn(name = "patientId", nullable = false) //This creates a FOREIGN KEY column in appointments table called "patientId"
    private PatientEntity patient; // JPA stores the FULL PatientEntity object! ,
    // So when you fetch an Appointment —you get the COMPLETE patient data inside it!
}
