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
    public ResponseEntity<BaseResponse<StudentDO>> createStudent(@RequestBody StudentDO studentDO) {
        BaseResponse<StudentDO> response = new BaseResponse<>();
        try {
            // Your business logic to fetch or process data
            StudentDO createdStudent = studentService.createStudent(studentDO);
            response.setData(createdStudent);
            response.setSuccess(true);
            response.setMessage("Student added successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/retrieveAllStudents")
    public ResponseEntity<BaseResponse<List<StudentDO>>> retrieveAllStudentsBasedOnAdultID() {
        BaseResponse<List<StudentDO>> response = new BaseResponse<>();
        try {
            // Your business logic to fetch or process data
            List<StudentDO> studentDOList = studentService.retrieveStudents();
            response.setSuccess(true);
            response.setMessage("Students retrieved successfully.");
            response.setData(studentDOList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
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
            throw e;
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<BaseResponse<StudentDO>> editStudent(@RequestBody StudentDO studentDO) {
        BaseResponse<StudentDO> response = new BaseResponse<>();
        try {
            StudentDO editedStudent = studentService.editStudent(studentDO);
            response.setData(editedStudent);
            response.setSuccess(true);
            response.setMessage("Student edited successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/retrieveStudents/{adultId}")
    public ResponseEntity<BaseResponse<List<StudentDO>>> retrieveAllStudentsBasedOnAdultID(@PathVariable String adultId) {
        BaseResponse<List<StudentDO>> response = new BaseResponse<>();
        try {
            // Your business logic to fetch or process data
            List<StudentDO> studentDOList = studentService.retrieveStudentsByAdultId(adultId);
            response.setSuccess(true);
            response.setMessage("Students retrieved successfully.");
            response.setData(studentDOList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw e;
        }
    }
}
