package com.microservice.course.controllers;

import com.microservice.course.entities.Course;
import com.microservice.course.exception.CourseExistsException;
import com.microservice.course.exception.RequestValidationException;
import com.microservice.course.logger.Log;
import com.microservice.course.services.impl.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/course")
@Tag(
        name = "Course",
        description = "It handle all about course data"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Succes", content = @Content(schema = @Schema(implementation = Course.class))),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = BindingResult.class))),
        @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(implementation = String.class))),

})
public class CourseController {

    private final CourseService courseService;
    private final RestTemplateConfig restTemplateConfig;

    public CourseController(CourseService courseService, RestTemplateConfig restTemplateConfig) {
        this.courseService = courseService;
        this.restTemplateConfig = restTemplateConfig;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new course",
            description = "Given information from the course, it creates a new one")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created", content = @Content(schema = @Schema(implementation = Integer.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = BindingResult.class))
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Entry with course data required for the registration", required = true,
            content = @Content(schema = @Schema(implementation = Course.class)))
    public ResponseEntity<?> registerCourse(@Valid @RequestBody Course course, BindingResult bindingResult) {

        try {
            validation(course.getCourseId());
            course = courseService.createCourse(course);
        } catch (RequestValidationException rve) {
            Log.logError(rve.getMessage(), rve);
            return new ResponseEntity<>(rve.getHttpMessage(), rve.getHttpStatus());
        }
        Log.logInfo("Course created");
        return new ResponseEntity<>(course.getCourseId(), HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a course ",
            description = "Given an id, it can delete the course from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "course deleted", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Problemas with the entry", content = @Content(schema = @Schema(implementation = BindingResult.class))),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Entry with course's id required for removing it", required = true,
            content = @Content(schema = @Schema(implementation = Course.class)))
    public ResponseEntity<?> deleteCourse(@PathVariable Long id)  {
        try {
            validation(id);
            courseService.deleteCourse(id);
        } catch (Exception e) {
            Log.logError(e.getMessage(), e);
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
        Log.logInfo("Course deleted");
        return new ResponseEntity<>("Course eliminated", HttpStatus.ACCEPTED);
    }

    @PostMapping("/modify/{id}")
    @Operation(summary = "update a course ",
            description = "Given an id, it finds the course and updates it with the information given")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "course updated", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Problemas with the entry", content = @Content(schema = @Schema(implementation = BindingResult.class))),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Entry with course's id required for the update", required = true,
            content = @Content(schema = @Schema(implementation = Course.class)))
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Map<String, Object> updates)  {

        try{
           validation(id);
           courseService.updateCourse(id, updates);
        }catch (Exception e) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("course updated", HttpStatus.OK);
    }


    @GetMapping("/get/{id}")
    @Operation(summary = "give a course ",
            description = "Given an id, it finds the course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "course found", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Problemas with the entry", content = @Content(schema = @Schema(implementation = BindingResult.class))),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Entry with course's id required for findinf it", required = true,
            content = @Content(schema = @Schema(implementation = Course.class)))
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        try{
            validation(id);
            courseService.getCourseById(id);
            return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.FOUND);
        }catch (Exception e) {
            Log.logError(e.getMessage(), e);
            return new ResponseEntity<>("course not found", HttpStatus.NOT_FOUND);
        }
    }

    private void validation(Long id) throws RequestValidationException {
        if (courseService.courseExists(id)) {
            throw new CourseExistsException("Course already exists with this id");
        }
    }

    @GetMapping("/students/{idCourse}")
    public ResponseEntity<?> getCourseWithStudents(@PathVariable Long idCourse) {
        try{
            validation(idCourse);
            Course course = courseService.getCourseWithStudents(idCourse);
            return new ResponseEntity<>(course, HttpStatus.OK);
        }catch (Exception e) {
            Log.logError(e.getMessage(), e);
            return new ResponseEntity<>("problems with data", HttpStatus.BAD_REQUEST);
        }
    }
}
