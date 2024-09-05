package com.microservice.course.mappers;

import com.microservice.course.entities.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Insert("INSERT INTO courses (course_name, course_description, course_start_date, course_end_date) " +
            "VALUES (#{courseName}, #{courseDescription}, #{courseStartDate}, #{courseEndDate})")
    @Options(useGeneratedKeys = true, keyProperty = "course_id")
    Course createCourse(Course course);

    @Select("SELECT  EXISTS(SELECT 1 FROM course WHERE course_id=#{id})")
    boolean existsCourse(Long idCourse);

    @Delete("DELETE FROM courses WHERE course_id= #{courseId}")
    void deleteCourse(Long idCourse);

    @Update("UPDATE courses SET course_name = #{courseName}, course_description = #{courseDescription}, " +
            "course_start_date = #{courseStartDate}, course_end_date = #{CourseEndDate}, course_updated_at = CURRENT_TIMESTAMP " +
            "WHERE course_id = #{courseId}")
    void updateCourse(Course course);


    @Select("SELECT course_id, course_name, course_description, course_start_date, course_end_date, course_created_at, course_updated_at " +
            "FROM courses WHERE course_id = #{courseId}")
    Course getCourseById(Long id);

    @Select("SELECT course_id, course_name, course_description, course_start_date, course_end_date, course_created_at, course_updated_at FROM courses")
    List<Course> getAllCourses();
}
