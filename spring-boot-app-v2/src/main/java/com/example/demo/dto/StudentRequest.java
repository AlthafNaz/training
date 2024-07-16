package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

//@Data
public record StudentRequest(
        int id,
        LocalDate dateOfBirth,
        String name,
        Double average
) {
}
