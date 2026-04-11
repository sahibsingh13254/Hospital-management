package com.example.assesment.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data  // generates getters, setters, toString
@NoArgsConstructor    //  generates no-arg constructor
@AllArgsConstructor   // generates all-arg constructor
@Table(name ="patients")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // For Auto-Increment
    private Long patientId;
    private String patientName;
    private Integer age;
    private String gender;
    private String phone_Number;
}
