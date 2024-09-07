package com.microservice.users.services.impl;

import com.microservice.users.entities.Teacher;
import com.microservice.users.mappers.TeacherMapper;
import com.microservice.users.services.ITeacherService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TeacherService implements ITeacherService {

    private final TeacherMapper teacherMapper;

    public TeacherService(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        teacherMapper.createTeacher(teacher);
        return teacher;
    }

    @Override
    public boolean teacherExists(String dni) {
        return teacherMapper.existTeacher(dni);
    }

    @Override
    public boolean deleteTeacher(String dni) {
        return teacherMapper.deleteTeacher(dni);
    }

    @Override
    public boolean updateTeacher(String dni, Map<String, Object> updates) {
        Teacher teacher = teacherMapper.getTeacher(dni);

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    teacher.setName((String) value);
                    break;
                case "firstSurname":
                    teacher.setFirstSurname((String) value);
                    break;
                case "lastSurname":
                    teacher.setLastSurname((String) value);
                    break;
                case "email":
                    teacher.setEmail((String) value);
                    break;
                case "phone":
                    teacher.setPhone((String) value);
                    break;
                case "address":
                    teacher.setAddress((String) value);
                    break;
            }
        });

        return teacherMapper.updateTeacher(teacher);
    }

    @Override
    public Teacher getTeacher(String dni) {
        return teacherMapper.getTeacher(dni);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherMapper.getTeacherById(id);
    }


}
