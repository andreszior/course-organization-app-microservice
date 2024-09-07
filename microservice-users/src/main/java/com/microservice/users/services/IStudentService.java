package com.microservice.users.services;


import com.microservice.users.entities.Student;

import java.util.Map;

public interface IStudentService {

    Student createStudent(Student student);

    boolean studentExists(String dni);

    boolean deleteStudent(String dni);

    boolean updateStudent(String dni, Map<String, Object> updates);

    Student getStudent(String dni);

    Student getStudentById(Long id);
}
