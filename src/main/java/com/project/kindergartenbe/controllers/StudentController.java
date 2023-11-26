package com.project.kindergartenbe.controllers;

import com.project.kindergartenbe.model.BaseResponse;
import com.project.kindergartenbe.model.dos.StudentDO;
import com.project.kindergartenbe.services.StudentService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<String>> createStudent(@RequestBody StudentDO studentDO) {
        BaseResponse<String> response = new BaseResponse<>();
        try {
            // Your business logic to fetch or process data
            studentService.createStudent(studentDO);
            response.setSuccess(true);
            response.setMessage("Student added successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Exception during adding the student: " + ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    @GetMapping("/retrieveAllStudents")
    public ResponseEntity<BaseResponse<List<StudentDO>>> retrieveAllStudents() {
        BaseResponse<List<StudentDO>> response = new BaseResponse<>();
        try {
            // Your business logic to fetch or process data
            List<StudentDO> studentDOList = studentService.retrieveStudents();
            response.setSuccess(true);
            response.setMessage("Students retrieved successfully.");
            response.setData(studentDOList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            response.setSuccess(false);
            response.setMessage("Error occurred while retrieving students.");
            throw e;
        }
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<BaseResponse<String>> deleteStudent(@PathVariable Long id) {
        BaseResponse<String> response = new BaseResponse<>();
        try {
            studentService.deleteStudent(id);
            response.setSuccess(true);
            response.setMessage("Student deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error occurred while deleting student.");
            throw e;
        }
    }
}
