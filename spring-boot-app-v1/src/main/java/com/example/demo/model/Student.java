package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection= "students")
public class Student {

    //    @Id
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private Double average;

    public Student(int id, String name, LocalDate dateOfBirth, Double average) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.average = average;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }
}
