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

    @DeleteMapping("/delete/{vaccineId}")
    public ResponseEntity<BaseResponse<String>> deleteVaccine(@PathVariable Long vaccineId) {
        BaseResponse<String> response = new BaseResponse<>();
        try {
            vaccineService.deleteStudentVaccine(vaccineId);
            response.setSuccess(true);
            response.setMessage("Vaccine deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error occurred while deleting Vaccine.");
            throw e;
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<BaseResponse<VaccineDO>> editVaccine(@RequestBody VaccineDO vaccineDO) {
        BaseResponse<VaccineDO> response = new BaseResponse<>();
        try {
            VaccineDO editedVaccine = vaccineService.editVaccine(vaccineDO);
            response.setData(editedVaccine);
            response.setSuccess(true);
            response.setMessage("Vaccine edited successfully.");
            return  ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error occurred while editing vaccine.");
            throw e;
        }
    }
}
