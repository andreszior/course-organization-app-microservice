package com.microservice.course.services.impl;

import com.microservice.course.entities.Course;
import com.microservice.course.entities.StudentDTO;
import com.microservice.course.mappers.CourseMapper;
import com.microservice.course.services.ICourseService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService {

    private CourseMapper courseMapper;

    private final String STUDENT_SERVICE_URL = "http://students-service/students/";

    public CourseService(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public Course createCourse(Course course) {
        return courseMapper.createCourse(course);
    }

    @Override
    public boolean courseExists(Long idCourse) {
        return courseMapper.existsCourse(idCourse);
    }

    @Override
    public void deleteCourse(Long idCourse) {
        courseMapper.deleteCourse(idCourse);
    }

    @Override
    public void updateCourse(Long idCourse, Map<String, Object> updates) {
        Course course = courseMapper.getCourseById(idCourse);

        updates.forEach((key, value) -> {
            switch (key) {
                case "courseName":
                    course.setCourseName((String) value);
                    break;
                case "courseDescription":
                    course.setCourseDescription((String) value);
                    break;
                case "courseStartDate":
                    course.setCourseStartDate((LocalDate) value);
                    break;
                case "courseEndDate":
                    course.setCourseEndDate((LocalDate) value);
                    break;
                case "courseStudents":
                    List<StudentDTO> students = course.getCourseStudents().stream()
                            .map(studentId -> restTemplate.getForObject(STUDENT_SERVICE_URL + studentId, StudentDTO.class))
                            .collect(Collectors.toList());
                    course.setCourseStudents(students);
                    break;

            }
        });
    }

    @Override
    public Course getCourseById(Long idCourse) {
        return courseMapper.getCourseById(idCourse);
    }

    @Override
    public Course getCourseWithStudents(Long idCourse) {
        Course course = courseMapper.getCourseById(idCourse);

        List<StudentDTO> students = course.getCourseStudents().stream()
                .map(studentId -> restTemplate.getForObject(STUDENT_SERVICE_URL + studentId, StudentDTO.class))
                .collect(Collectors.toList());

        return Course.builder().courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .courseStudents(students)
                .build();
    }
}
