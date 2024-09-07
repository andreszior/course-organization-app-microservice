package com.microservice.users.controllers;

import com.microservice.users.entities.Teacher;
import com.microservice.users.loggers.Log;
import com.microservice.users.services.impl.TeacherService;
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
@RequestMapping("/teacher")
@Tag(
        name = "Teacher",
        description = "This controller control all data about teachers"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Wrong request", content = @Content(schema = @Schema(implementation = BindingResult.class))),
        @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(schema = @Schema(implementation = String.class))),
})
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @PostMapping("/register")
    @Operation(summary = "Register a new Teacher ",
            description= "Given information from the teacher, it creates a new entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Teacher created", content = @Content(schema = @Schema(implementation = Integer.class))),
            @ApiResponse(responseCode = "400", description = "Problems with the entry", content = @Content(schema = @Schema(implementation = BindingResult.class))
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Entry with teacher data required for the registration", required = true,
    content = @Content(schema = @Schema(implementation = Teacher.class)))
    public ResponseEntity<?> registerTeacher(@Valid @RequestBody Teacher teacher, BindingResult bindingResult) {
        try{
            validation(teacher.getDni());
            teacher = teacherService.createTeacher(teacher);

        } catch (RequestValidationException rve) {
            Log.logError(rve.getMessage(), rve);
            return new ResponseEntity<>(rve.getHttpMessage(), rve.getHttpStatus());
        } catch (Exception e) {
            Log.logError(e.getMessage(), e);
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }

        Log.logInfo("teacher registered");
        return new ResponseEntity<>(teacher.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{dni}")
    @Operation(summary = "Delete a teacher ",
        description = "Given a dni, it can delete the teacher from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher delete", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Problemas with the entry", content = @Content(schema = @Schema(implementation = BindingResult.class))),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Entry with teacher's DNI required for the deletion", required = true,
    content = @Content(schema = @Schema(implementation = Teacher.class)))
    public ResponseEntity<?> deleteTeacher(@PathVariable String dni){
        try{
            teacherService.deleteTeacher(dni);
        } catch (Exception e){
            Log.logError(e.getMessage(), e);
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }

        Log.logInfo("Teacher deleted");
        return new ResponseEntity<>("Teacher eliminated", HttpStatus.ACCEPTED);
    }


    private void validation(String dni) throws RequestValidationException {
        if(teacherService.teacherExists(dni)){
            throw new PersonAlreadyExistsException("User exists with this dni.");
        }
    }

    @PostMapping("/modify/{dni}")
    public ResponseEntity<?> updateTeacher(@PathVariable String dni, @RequestBody Map<String, Object> updates){

        if(teacherService.updateTeacher(dni, updates)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/{dni}")
    public ResponseEntity<?> getTeacher(@PathVariable String dni){
        if(teacherService.getTeacher(dni) != null) {
            return new ResponseEntity<>(teacherService.getTeacher(dni), HttpStatus.OK);
        }
        return new ResponseEntity<>("Teacher not found", HttpStatus.NOT_FOUND);
    }

}
