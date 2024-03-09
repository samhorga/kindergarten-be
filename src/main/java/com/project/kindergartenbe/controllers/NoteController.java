package com.project.kindergartenbe.controllers;

import com.project.kindergartenbe.model.BaseResponse;
import com.project.kindergartenbe.model.dos.NoteDO;
import com.project.kindergartenbe.model.dos.StudentDO;
import com.project.kindergartenbe.services.NoteService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/create/{studentId}")
    public ResponseEntity<BaseResponse<String>> createNote(@RequestBody NoteDO noteDO, @PathVariable Long studentId) {
        BaseResponse<String> response = new BaseResponse<>();
        try {
            noteService.createNote(noteDO, studentId);
            response.setSuccess(true);
            response.setMessage("Note added successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Exception during adding the student: " + ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    @GetMapping("/retrieveStudentNotes/{studentId}")
    public ResponseEntity<BaseResponse<List<StudentDO>>> retrieveStudentNotes(@PathVariable String studentId) {
        BaseResponse<List<StudentDO>> response = new BaseResponse<>();
        try {
            // Your business logic to fetch or process data
//            List<StudentDO> studentDOList = studentService.retrieveStudents();
//            response.setSuccess(true);
//            response.setMessage("Notes retrieved successfully.");
//            response.setData(studentDOList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            response.setSuccess(false);
            response.setMessage("Error occurred while retrieving notes.");
            throw e;
        }
    }

    @DeleteMapping("/delete/{noteId}/{studentId}")
    public ResponseEntity<BaseResponse<String>> deleteNote(@PathVariable Long noteId, @PathVariable Long studentId) {
        BaseResponse<String> response = new BaseResponse<>();
        try {
//            studentService.deleteStudent(id);
            response.setSuccess(true);
            response.setMessage("Note deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error occurred while deleting note.");
            throw e;
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<BaseResponse<NoteDO>> editNote(@RequestBody NoteDO noteDO, @PathVariable Long studentID) {
        BaseResponse<NoteDO> response = new BaseResponse<>();
        try {
//            StudentDO editedStudent = studentService.editStudent(studentDO);
            response.setData(null);
            response.setSuccess(true);
            response.setMessage("Note edited successfully.");
            return  ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error occurred while editing note.");
            throw e;
        }
    }
}
