package com.project.kindergartenbe.controllers;

import com.project.kindergartenbe.model.BaseResponse;
import com.project.kindergartenbe.model.dos.AllergyDO;
import com.project.kindergartenbe.model.dos.VaccineDO;
import com.project.kindergartenbe.services.AllergyService;
import com.project.kindergartenbe.services.VaccineService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vaccine")
public class VaccineController {

    private final VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @PostMapping("/create/{studentId}")
    public ResponseEntity<BaseResponse<VaccineDO>> createVaccine(@RequestBody VaccineDO vaccineDO,
                                                                 @PathVariable Long studentId) {
        BaseResponse<VaccineDO> response = new BaseResponse<>();
        try {
            VaccineDO savedVaccine = vaccineService.createVaccine(vaccineDO, studentId);
            response.setData(savedVaccine);
            response.setSuccess(true);
            response.setMessage("Vaccine added successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Exception during adding the vaccine: " + ExceptionUtils.getStackTrace(e));
            throw e;
        }
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

//    @PostMapping("/edit")
//    public ResponseEntity<BaseResponse<AllergyDO>> editAllergy(@RequestBody AllergyDO allergyDO) {
//        BaseResponse<AllergyDO> response = new BaseResponse<>();
//        try {
//            AllergyDO editedAllergy = allergyService.editAllergy(allergyDO);
//            response.setData(editedAllergy);
//            response.setSuccess(true);
//            response.setMessage("Allergy edited successfully.");
//            return  ResponseEntity.ok(response);
//        } catch (Exception e) {
//            response.setSuccess(false);
//            response.setMessage("Error occurred while editing allergy.");
//            throw e;
//        }
//    }
//}
