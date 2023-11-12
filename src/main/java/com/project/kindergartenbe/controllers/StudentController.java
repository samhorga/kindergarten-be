package com.project.kindergartenbe.controllers;

import com.project.kindergartenbe.model.dos.StudentDO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @RequestMapping("/create")
    public void createStudent(@RequestBody StudentDO studentDO) {

    }
}
