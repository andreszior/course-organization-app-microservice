package com.microservice.course.exception;

import org.springframework.http.HttpStatus;

public class CourseExistsException extends RequestValidationException{

    public CourseExistsException(String exceptionMessage) {
        super(exceptionMessage);
    }

    @Override
    HttpStatus setHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    String setHttpMessage() {
        return "el curso ya existe";
    }
}
