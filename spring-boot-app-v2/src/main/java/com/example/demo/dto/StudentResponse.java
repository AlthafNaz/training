package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

//@Data
public record StudentResponse (
        int id,
        String name,
        Double average
) { }
