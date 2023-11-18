package com.project.kindergartenbe.controllers;

import com.project.kindergartenbe.model.BaseResponse;
import com.project.kindergartenbe.model.dos.StudentDO;
import com.project.kindergartenbe.services.StudentService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
