package com.project.kindergartenbe.controllers;

import com.project.kindergartenbe.model.BaseResponse;
import com.project.kindergartenbe.model.dos.SchoolDO;
import com.project.kindergartenbe.model.dos.StudentDO;
import com.project.kindergartenbe.services.SchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
public class SchoolController {

    private SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public ResponseEntity<SchoolDO> createSchool(@RequestBody SchoolDO school) {
        SchoolDO createdSchool = schoolService.createSchool(school);
        return new ResponseEntity<>(createdSchool, HttpStatus.CREATED);
    }

//    // Endpoint to retrieve all schools
//    @GetMapping
//    public ResponseEntity<List<School>> getAllSchools() {
//        List<School> schools = schoolService.getAllSchools();
//        if (schools.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(schools, HttpStatus.OK);
//    }
//
//    // Endpoint to retrieve a specific school by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<School> getSchoolById(@PathVariable Long id) {
//        School school = schoolService.getSchoolById(id);
//        if (school == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(school, HttpStatus.OK);
//    }
//
//    // Endpoint to update an existing school
//    @PutMapping("/{id}")
//    public ResponseEntity<School> updateSchool(@PathVariable Long id, @RequestBody School school) {
//        School updatedSchool = schoolService.updateSchool(id, school);
//        if (updatedSchool == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(updatedSchool, HttpStatus.OK);
//    }
//
//    // Endpoint to delete a school by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
//        boolean deleted = schoolService.deleteSchool(id);
//        if (!deleted) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
