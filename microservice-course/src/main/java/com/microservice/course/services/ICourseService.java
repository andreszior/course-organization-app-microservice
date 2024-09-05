package com.microservice.course.services;

import com.microservice.course.entities.Course;

import java.util.Map;

public interface ICourseService {

    Course createCourse(Course course);

    boolean courseExists(Long idCourse);

    void deleteCourse(Long idCourse);

    void updateCourse(Long idCourse, Map<String, Object> updates);

    Course getCourseById(Long idCourse);

    Course getCourseWithStudents(Long idCourse);
}
