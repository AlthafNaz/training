package com.example.demo.dto;

import java.time.LocalDate;

public record StudentRequest(
        int id,
        LocalDate dateOfBirth,
        String name,
        Double average
) {
}
