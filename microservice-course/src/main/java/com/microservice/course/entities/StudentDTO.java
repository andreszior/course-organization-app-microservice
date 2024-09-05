package com.microservice.course.entities;


public class StudentDTO {

    private Long id;
    private String studentName;
    private String studentFirstLastName;
    private String studentSecondLastName;


    public StudentDTO() {}

    public StudentDTO(Long id, String studentName, String studentFirstLastName, String studentSecondLastName) {
        this.id = id;
        this.studentName = studentName;
        this.studentFirstLastName = studentFirstLastName;
        this.studentSecondLastName = studentSecondLastName;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentFirstLastName() {
        return studentFirstLastName;
    }

    public void setStudentFirstLastName(String studentFirstLastName) {
        this.studentFirstLastName = studentFirstLastName;
    }
    public String getStudentSecondLastName() {
        return studentSecondLastName;
    }
    public void setStudentSecondLastName(String studentSecondLastName) {
        this.studentSecondLastName = studentSecondLastName;
    }
}
