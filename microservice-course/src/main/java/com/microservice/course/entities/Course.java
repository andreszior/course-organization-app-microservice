package com.microservice.course.entities;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public class Course {

    private Long courseId;
    private String courseName;
    private String courseDescription;
    private LocalDate courseStartDate;
    private LocalDate courseEndDate;
    private LocalDateTime courseCreatedAt;
    private LocalDateTime courseUpdatedAt;
    private List<StudentDTO> courseStudents;

    public Course() {}

    public Course(Long courseId, String courseName, String courseDescription, LocalDate courseStartDate, LocalDate courseEndDate) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
    }

    public Course(Long courseId, String courseName, String courseDescription, LocalDate courseStartDate, LocalDate courseEndDate, LocalDateTime courseCreatedAt, LocalDateTime courseUpdatedAt, List<StudentDTO> courseStudents) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseCreatedAt = courseCreatedAt;
        this.courseUpdatedAt = courseUpdatedAt;
        this.courseStudents = courseStudents;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public LocalDate getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(LocalDate courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public LocalDate getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(LocalDate courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public LocalDateTime getCourseCreatedAt() {
        return courseCreatedAt;
    }

    public void setCourseCreatedAt(LocalDateTime courseCreatedAt) {
        this.courseCreatedAt = courseCreatedAt;
    }

    public LocalDateTime getCourseUpdatedAt() {
        return courseUpdatedAt;
    }

    public void setCourseUpdatedAt(LocalDateTime courseUpdatedAt) {
        this.courseUpdatedAt = courseUpdatedAt;
    }

    public List<StudentDTO> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(List<StudentDTO> courseStudents) {
        this.courseStudents = courseStudents;
    }
}
