package com.project.kindergartenbe.controllers;

import com.project.kindergartenbe.model.dos.GroupDO;
import com.project.kindergartenbe.model.dos.SchoolDO;
import com.project.kindergartenbe.services.GroupService;
import com.project.kindergartenbe.services.SchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/{schoolId}")
    public ResponseEntity<GroupDO> createGroup(@PathVariable Long schoolId, @RequestBody GroupDO group) {
        GroupDO createdSchool = groupService.createGroup(schoolId, group);
        return new ResponseEntity<>(createdSchool, HttpStatus.CREATED);
    }

    // Endpoint to retrieve all schools
    @GetMapping
    public ResponseEntity<List<GroupDO>> getAllSchools() {
        List<GroupDO> schools = groupService.getAllGroups();
        if (schools.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(schools, HttpStatus.OK);
    }
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
    // Endpoint to update an existing school
    @PutMapping("/{id}")
    public ResponseEntity<GroupDO> updateGroup(@PathVariable Long id, @RequestBody GroupDO group) {
        GroupDO updatedGroup = groupService.updateGroup(id, group);
        if (updatedGroup == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
    }

    // Endpoint to delete a group by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        boolean deleted = groupService.deleteGroup(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
