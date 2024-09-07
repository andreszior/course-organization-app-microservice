package com.microservice.users.mappers;

import com.microservice.users.entities.Student;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StudentMapper {

    @Insert("INSERT INTO students (student_dni, student_name, student_firstsurname, student_lastsurname, " +
            "student_email, student_phone, student_address, student_course) " +
            "VALUES (#{dni}, #{name}, #{firstSurname}, #{lastSurname}, #{email}, #{phone}, #{address}, #{idCourse})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "student_id")
    void createStudent(Student student);

    @Select("SELECT EXISTS(SELECT 1 FROM students WHERE student_dni = #{dni})")
    boolean studentExists(String dni);

    @Delete("DELETE FROM students WHERE student_dni = #{dni}")
    boolean deleteStudent(String dni);

    @Update("UPDATE students SET student_dni = #{dni}, student_name=#{name}, student_firstsurname=#{firstSurname}, " +
            "student_lastsurname=#{lastSurname}, student_email=#{email}, student_phone=#{phone}, " +
            "student_address=#{address}, student_course=#{idCourse} WHERE student_dni=#{dni}")
    boolean updateStudent(Student student);

    @Select("SELECT student_id AS id, student_dni AS dni, student_name AS name, student_firstsurname AS firstSurname, " +
            "student_lastsurname AS lastSurname, student_email AS email, student_phone AS phone, student_address AS address, " +
            "student_course AS idCourse FROM students WHERE student_dni = #{dni}")
    Student getStudent(String dni);

    @Select("SELECT student_id AS id, student_dni AS dni, student_name AS name, student_firstsurname AS firstSurname, " +
            "student_lastsurname AS lastSurname, student_email AS email, student_phone AS phone, student_address AS address, " +
            "student_course AS idCourse FROM students WHERE student_id = #{id}")
    Student getStudentById(Long idStudent);
}

