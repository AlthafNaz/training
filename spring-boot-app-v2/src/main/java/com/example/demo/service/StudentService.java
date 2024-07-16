package com.example.demo.service;

import com.example.demo.dto.StudentRequest;
import com.example.demo.dto.StudentResponse;
import com.example.demo.dto.StudentMapper;
//import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        log.info("All students retrieved");
        return students.stream().map(studentMapper::ToResponseDTO).collect(Collectors.toList());
    }

//    public Optional<StudentResponse> getStudentById(int id) {
//        log.info("Fetching student with id {}", id);
//        return studentRepository.findById(id).map(studentMapper::ToResponseDTO);
//    }

    public Optional<StudentResponse> getStudentById(int id) {
        log.info("Fetching student with id {}", id);
        return studentRepository.findById(id)
                .map(studentMapper::ToResponseDTO)
                .or(() -> {
                    log.error("Student with id {} not found", id);
                    return Optional.empty();
                });
    }

    public StudentResponse addStudent(StudentRequest studentRequest) {
        Student student = studentMapper.toEntity(studentRequest);
        student = studentRepository.save(student);
        log.info("Added new student: {}", student);
        return studentMapper.ToResponseDTO(student);
    }

    public void deleteStudentById(int id) {
        if (!studentRepository.existsById(id)) {
            log.error("Student with id {} not found", id);
            throw new NoSuchElementException("Student with id " + id + " not found");
        }
        studentRepository.deleteById(id);
        log.info("Deleted student with id {}", id);
    }
}