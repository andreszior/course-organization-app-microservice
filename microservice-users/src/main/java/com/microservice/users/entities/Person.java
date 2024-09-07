package com.microservice.users.entities;

public abstract class Person {

    private Long id;
    private String dni;
    private String name;
    private String firstSurname;
    private String lastSurname;
    private String email;
    private String phone;
    private String address;

    public Person() {}

    public Person(String dni, String name, String firstSurname, String lastSurname, String email, String phone, String address) {
        //this.id = id;
        this.dni = dni;
        this.name = name;
        this.firstSurname = firstSurname;
        this.lastSurname = lastSurname;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String lastnameFirst) {
        this.firstSurname = lastnameFirst;
    }

    public String getLastSurname() {
        return lastSurname;
    }

    public void setLastSurname(String lastnameLast) {
        this.lastSurname = lastnameLast;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
