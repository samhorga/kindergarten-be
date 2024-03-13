package com.project.kindergartenbe.controllers;

import com.project.kindergartenbe.model.BaseResponse;
import com.project.kindergartenbe.model.dos.AllergyDO;
import com.project.kindergartenbe.services.AllergyService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/allergy")
public class AllergyController {

    private final AllergyService allergyService;

    public AllergyController(AllergyService allergyService) {
        this.allergyService = allergyService;
    }

    @PostMapping("/create/{studentId}")
    public ResponseEntity<BaseResponse<AllergyDO>> createAllergy(@RequestBody AllergyDO allergyDO, @PathVariable Long studentId) {
        BaseResponse<AllergyDO> response = new BaseResponse<>();
        try {
            AllergyDO savedAllergy = allergyService.createAllergy(allergyDO, studentId);
            response.setData(savedAllergy);
            response.setSuccess(true);
            response.setMessage("Allergy added successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Exception during adding the allergy: " + ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

//    @DeleteMapping("/delete/{noteId}")
//    public ResponseEntity<BaseResponse<String>> deleteNote(@PathVariable Long noteId) {
//        BaseResponse<String> response = new BaseResponse<>();
//        try {
//            noteService.deleteStudentNote(noteId);
//            response.setSuccess(true);
//            response.setMessage("Note deleted successfully.");
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            response.setSuccess(false);
//            response.setMessage("Error occurred while deleting note.");
//            throw e;
//        }
//    }
//
//    @PostMapping("/edit")
//    public ResponseEntity<BaseResponse<NoteDO>> editNote(@RequestBody NoteDO noteDO) {
//        BaseResponse<NoteDO> response = new BaseResponse<>();
//        try {
//            NoteDO editedNote = noteService.editNote(noteDO);
//            response.setData(editedNote);
//            response.setSuccess(true);
//            response.setMessage("Note edited successfully.");
//            return  ResponseEntity.ok(response);
//        } catch (Exception e) {
//            response.setSuccess(false);
//            response.setMessage("Error occurred while editing note.");
//            throw e;
//        }
//    }
}
