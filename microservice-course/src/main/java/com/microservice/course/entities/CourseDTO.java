package com.microservice.course.entities;

import java.time.LocalDateTime;
import java.util.List;

public class CourseDTO {

    private Long id;
    private String courseName;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<StudentDTO> students;

    // Constructores
    public CourseDTO() {}

    public CourseDTO(Long id, String courseName, String description, LocalDateTime startDate, LocalDateTime endDate, List<StudentDTO> students) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.students = students;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }
}
