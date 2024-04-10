package com.project.kindergartenbe.controllers;

import com.project.kindergartenbe.model.BaseResponse;
import com.project.kindergartenbe.model.dos.AdultDO;
import com.project.kindergartenbe.model.dos.AllergyDO;
import com.project.kindergartenbe.services.AdultService;
import com.project.kindergartenbe.services.AllergyService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adult")
public class AdultController {

    private final AdultService adultService;

    public AdultController(AdultService adultService) {
        this.adultService = adultService;
    }

    @PostMapping("/create/{studentId}")
    public ResponseEntity<BaseResponse<AdultDO>> createAdult(@RequestBody AdultDO adultDO, @PathVariable Long studentId) {
        BaseResponse<AdultDO> response = new BaseResponse<>();
        try {
            AdultDO savedAdult = adultService.createAdult(adultDO, studentId);
            response.setData(savedAdult);
            response.setSuccess(true);
            response.setMessage("Adult added successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Exception during adding the adult: " + ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

//    @DeleteMapping("/delete/{allergyId}")
//    public ResponseEntity<BaseResponse<String>> deleteAllergy(@PathVariable Long allergyId) {
//        BaseResponse<String> response = new BaseResponse<>();
//        try {
//            allergyService.deleteStudentAllergy(allergyId);
//            response.setSuccess(true);
//            response.setMessage("Allergy deleted successfully.");
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            response.setSuccess(false);
//            response.setMessage("Error occurred while deleting Allergy.");
//            throw e;
//        }
//    }
//
    @PostMapping("/edit")
    public ResponseEntity<BaseResponse<AdultDO>> editAdult(@RequestBody AdultDO adultDO) {
        BaseResponse<AdultDO> response = new BaseResponse<>();
        try {
            AdultDO editedAdult = adultService.editAdult(adultDO);
            response.setData(editedAdult);
            response.setSuccess(true);
            response.setMessage("Adult edited successfully.");
            return  ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error occurred while editing adult.");
            throw e;
        }
    }
}
