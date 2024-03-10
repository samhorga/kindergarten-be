package com.project.kindergartenbe.controllers;

import com.project.kindergartenbe.model.BaseResponse;
import com.project.kindergartenbe.model.dos.NoteDO;
import com.project.kindergartenbe.services.NoteService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/create/{studentId}")
    public ResponseEntity<BaseResponse<NoteDO>> createNote(@RequestBody NoteDO noteDO, @PathVariable Long studentId) {
        BaseResponse<NoteDO> response = new BaseResponse<>();
        try {
            NoteDO savedNote = noteService.createNote(noteDO, studentId);
            response.setData(savedNote);
            response.setSuccess(true);
            response.setMessage("Note added successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Exception during adding the student: " + ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    @DeleteMapping("/delete/{noteId}")
    public ResponseEntity<BaseResponse<String>> deleteNote(@PathVariable Long noteId) {
        BaseResponse<String> response = new BaseResponse<>();
        try {
            noteService.deleteStudentNote(noteId);
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
    public ResponseEntity<BaseResponse<NoteDO>> editNote(@RequestBody NoteDO noteDO) {
        BaseResponse<NoteDO> response = new BaseResponse<>();
        try {
            NoteDO editedNote = noteService.editNote(noteDO);
            response.setData(editedNote);
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
