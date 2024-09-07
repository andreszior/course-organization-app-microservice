package com.microservice.users.entities;

public class Student extends Person {

    private Long idCourse;

    public Student() {}

    public Student(String dni, String name, String firstSurname, String lastSurname, String email, String phone, String address, Long idCourse) {
        super(dni, name, firstSurname, lastSurname, email, phone, address);
        this.idCourse = idCourse;
    }

    public Long getCourse() {
        return idCourse;
    }

    public void setCourse(Long idCourse) {
        this.idCourse = idCourse;
    }
}
