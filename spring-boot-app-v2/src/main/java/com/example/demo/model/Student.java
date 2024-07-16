package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection= "students")
public class Student {

    //    @Id
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private Double average;

}
