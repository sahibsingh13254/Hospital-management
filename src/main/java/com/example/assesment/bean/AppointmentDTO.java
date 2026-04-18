package com.example.assesment.bean;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentDTO {


    private Long appointmentId;

    @NotNull(message = "Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;

    @NotNull(message = "Time is required")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime appointmentTime;

    @NotEmpty(message = "Doctor name is required")
    private String doctorName;

    private PatientDTO patient;

}
