package com.example.assesment.bean;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientBean {


    private Long patientId;

    @NotEmpty(message = "Patient name is required")
    private String patientName;

    @NotNull(message = "Age is required")
    @Size(min = 10, max = 100 , message = "Above 10 and below 100" )
    private Integer age;

    @NotNull(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    private String phone_Number;
}
