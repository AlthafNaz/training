package com.example.demo.dto;

import com.example.demo.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class StudentMapper {

    public StudentResponse ToResponseDTO(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getAverage()
        );
    }

    public Student toEntity(StudentRequest studentRequestDTO) {
        Student student = new Student();
        student.setId(studentRequestDTO.id());
        student.setName(studentRequestDTO.name());
        student.setDateOfBirth(studentRequestDTO.dateOfBirth());
        student.setAverage(studentRequestDTO.average());
        return student;
    }

}



