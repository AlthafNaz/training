package org.example;

import java.util.Arrays;

public class Main {

    public static int square(int value){
        return (value*value);
    }

    public static void main(String[] args) {
        Student student = new Student ();
        student.setId(1);
        student.setName("Jane Doe");
        student.setAge(20);
        student.setSubjects(Arrays.asList("Mathematics", "English", "History"));

        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Age: " + student.getAge());
        System.out.println("Subjects: " + student.getSubjects());

    }
}