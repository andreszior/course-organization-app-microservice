package com.microservice.users.controllers;

import com.microservice.users.entities.Student;
import com.microservice.users.loggers.Log;
import com.microservice.users.services.impl.StudentService;
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
@RequestMapping("/student")
@Tag(
        name = "Student",
        description = "This controller control all data about students"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Wrong request", content = @Content(schema = @Schema(implementation = BindingResult.class))),
        @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(schema = @Schema(implementation = String.class))),
})
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new student",
            description = "Given information from the student, it creates a new entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "student created",
                    content = @Content(schema = @Schema(implementation = Integer.class))),
            @ApiResponse(responseCode = "400", description = "Problems with the entry",
                    content = @Content(schema = @Schema(implementation = BindingResult.class))
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Entry with student data required for the registration", required = true,
            content = @Content(schema = @Schema(implementation = Student.class)))
    public ResponseEntity<?> registerStudent(@Valid @RequestBody Student student, BindingResult bindingResult) {
        try{
            validation(student.getDni());
            student = studentService.createStudent(student);
        }catch (RequestValidationException rve){
            Log.logError(rve.getMessage(), rve);
            return new ResponseEntity<>(rve.getHttpMessage(), rve.getHttpStatus());
        } catch (Exception e) {
            Log.logError(e.getMessage(), e);
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
        Log.logInfo("student registered");
        return new ResponseEntity<>(student.getId(), HttpStatus.CREATED);
    }

    private void validation(String dni) throws RequestValidationException {
        if(studentService.studentExists(dni)){
            throw new PersonAlreadyExistsException("User exists with this dni.");
        }
    }

    @DeleteMapping("/delete/{dni}")
    @Operation(summary = "Delete a student ",
            description = "Given a dni, it can delete the student from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "student delete", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Problemas with the entry", content = @Content(schema = @Schema(implementation = BindingResult.class))),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Entry with student's DNI required for the deletion", required = true,
            content = @Content(schema = @Schema(implementation = Student.class)))
    public ResponseEntity<?> deleteStudent(@PathVariable String dni){
        try{
            studentService.deleteStudent(dni);
        } catch (Exception e){
            Log.logError(e.getMessage(), e);
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }

        Log.logInfo("Student deleted");
        return new ResponseEntity<>("Student eliminated", HttpStatus.ACCEPTED);
    }

    @PostMapping("/modify/{dni}")
    public ResponseEntity<?> updateStudent(@PathVariable String dni, @RequestBody Map<String, Object> updates){

        if(studentService.updateStudent(dni, updates)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/{dni}")
    public ResponseEntity<?> getStudent(@PathVariable String dni){
        if(studentService.getStudent(dni) != null) {
            return new ResponseEntity<>(studentService.getStudent(dni), HttpStatus.OK);
        }
        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/id={id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
        if(studentService.getStudentById(id) != null) {
            return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("reg")
    public ResponseEntity<?> registerStudent2(@Valid Student student, BindingResult bindingResult) {
            student = new Student();
            student.setDni("12345658A");
            student.setName("John");
            student.setFirstSurname("Doe");
            student.setLastSurname("Smith");
            student.setEmail("john.doe@example.com");
            student.setPhone("123456789");
            student.setAddress("123 Main St");
            student.setCourse(1L); // Asegúrate de que el valor de idCourse está establecido

            studentService.createStudent(student);
            return new ResponseEntity<>(student.getId(), HttpStatus.CREATED);
    }


}
