package com.microservice.users.services;


import com.microservice.users.entities.Teacher;

import java.util.Map;

public interface ITeacherService {

    Teacher createTeacher(Teacher teacher);

    boolean teacherExists(String dni);

    boolean deleteTeacher(String dni);

    boolean updateTeacher(String dni, Map<String, Object> updates);

    Teacher getTeacher(String dni);

    Teacher getTeacherById(Long id);
}
