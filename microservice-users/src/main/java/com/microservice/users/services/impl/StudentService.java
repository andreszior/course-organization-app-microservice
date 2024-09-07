package com.microservice.users.services.impl;

import com.microservice.users.entities.Student;
import com.microservice.users.mappers.StudentMapper;
import com.microservice.users.services.IStudentService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentService implements IStudentService {

    private final StudentMapper studentMapper;

    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public Student createStudent(Student student) {
        studentMapper.createStudent(student);
        return student;
    }

    @Override
    public boolean studentExists(String dni) {
        return studentMapper.studentExists(dni);
    }

    @Override
    public boolean deleteStudent(String dni) {
        return studentMapper.deleteStudent(dni);
    }

    @Override
    public boolean updateStudent(String dni, Map<String, Object> updates) {
        Student student = studentMapper.getStudent(dni);

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    student.setName((String) value);
                    break;
                case "firstSurname":
                    student.setFirstSurname((String) value);
                    break;
                case "lastSurname":
                    student.setLastSurname((String) value);
                    break;
                case "email":
                    student.setEmail((String) value);
                    break;
                case "phone":
                    student.setPhone((String) value);
                    break;
                case "address":
                    student.setAddress((String) value);
                    break;
                case "idCourse":
                    student.setCourse(Long.valueOf((String) value));
                    break;
            }
        });

        return studentMapper.updateStudent(student);
    }

    @Override
    public Student getStudent(String dni) {
        return studentMapper.getStudent(dni);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentMapper.getStudentById(id);
    }
}
