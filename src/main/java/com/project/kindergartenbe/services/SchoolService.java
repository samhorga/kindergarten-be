package com.project.kindergartenbe.services;

import com.project.kindergartenbe.model.be.SchoolBE;
import com.project.kindergartenbe.model.dos.SchoolDO;
import com.project.kindergartenbe.repositories.SchoolRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    // Method to create a new school
    public SchoolDO createSchool(SchoolDO schoolDO) {
        SchoolBE schoolBE = new SchoolBE(schoolDO);
        schoolBE.setCreatedBy("SAM");
        schoolBE.setCreatedDate(LocalDateTime.now().toString());
        schoolBE.setLastEditedBy("SAM");
        schoolBE.setEditedDate(LocalDateTime.now().toString());
        SchoolBE createdSchool = schoolRepository.save(schoolBE);
        return new SchoolDO(createdSchool);
    }

    // Method to retrieve all schools
    public List<SchoolDO> getAllSchools() {
        List<SchoolBE> schoolBE = schoolRepository.findAll();
        return schoolBE.stream().map(SchoolDO::new).collect(Collectors.toList());
    }

    //
//    // Method to retrieve a specific school by ID
//    public SchoolDO getSchoolById(Long id) {
//        Optional<School> optionalSchool = schoolRepository.findById(id);
//        return optionalSchool.orElse(null);
//    }
//
    // Method to update an existing school
    public SchoolDO updateSchool(Long id, SchoolDO updatedSchool) {
        Optional<SchoolBE> optionalSchool = schoolRepository.findById(id);
        if (optionalSchool.isPresent()) {
            SchoolBE existingSchool = optionalSchool.get();
            existingSchool.setEditedDate(LocalDateTime.now().toString());
            existingSchool.setLastEditedBy("SAM");
            existingSchool.setDirectorFirstName(updatedSchool.getDirectorFirstName());
            existingSchool.setDirectorLastName(updatedSchool.getDirectorLastName());
            existingSchool.setEmail(updatedSchool.getEmail());
            existingSchool.setName(updatedSchool.getName());
            existingSchool.setPhoneNumber(updatedSchool.getPhoneNumber());
            // Update existingSchool with values from updatedSchool
            // Save and return the updatedSchool
            return new SchoolDO(schoolRepository.save(existingSchool));
        }
        return null;
    }

    // Method to delete a school by ID
    public boolean deleteSchool(Long id) {
        Optional<SchoolBE> optionalSchool = schoolRepository.findById(id);
        if (optionalSchool.isPresent()) {
            schoolRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
