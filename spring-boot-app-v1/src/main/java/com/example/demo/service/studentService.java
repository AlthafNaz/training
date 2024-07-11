package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class studentService {
//    private List<Student> students ;

//    public studentService(){
//        students = new ArrayList<>();
//        students.add(new Student(1, "John Doe", LocalDate.of(2000, 1, 1), 85.5));
//        students.add(new Student(2, "Jane Smith", LocalDate.of(1999, 2, 15), 90.0));
//        students.add(new Student(3, "Jim Brown", LocalDate.of(2001, 3, 20), 78.5));
//    }

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudentById(int id){
        studentRepository.deleteById(id);
    }
}


