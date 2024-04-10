package com.project.kindergartenbe.services;

import com.project.kindergartenbe.model.be.AdultBE;
import com.project.kindergartenbe.model.be.AllergyBE;
import com.project.kindergartenbe.model.be.StudentBE;
import com.project.kindergartenbe.model.dos.AdultDO;
import com.project.kindergartenbe.model.dos.AllergyDO;
import com.project.kindergartenbe.repositories.AdultRepository;
import com.project.kindergartenbe.repositories.AllergyRepository;
import com.project.kindergartenbe.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdultService {

    private final AdultRepository adultRepository;
    private final StudentRepository studentRepository;

//    private final CommonMapper commonMapper;

    public AdultService(StudentRepository studentRepository, AdultRepository adultRepository) {
        this.adultRepository = adultRepository;
        this.studentRepository = studentRepository;
    }


    public AdultDO createAdult(AdultDO adultDO, Long studentID) {
        AdultBE adultBE = new AdultBE(adultDO);

        adultBE.setCreatedDate(LocalDateTime.now().toString());
        adultBE.setEditedDate(LocalDateTime.now().toString());
        adultBE.setCreatedBy("SAMUEL HORGA");
        adultBE.setLastEditedBy("SAMUEL HORGA");

        Optional<StudentBE> optionalStudentBE = this.studentRepository.findById(studentID);

        optionalStudentBE.ifPresentOrElse(
                studentBE -> {
                    adultBE.setStudent(studentBE);
                    this.adultRepository.save(adultBE);
                },
                () -> {
                    throw new RuntimeException("Student not found with id: " + studentID);
                });
        return new AdultDO(adultBE);
    }

    public void deleteAdult(Long adultId) {
        AdultBE adultBE = adultRepository.findById(adultId).orElseThrow();
        adultRepository.delete(adultBE);
    }

    public AdultDO editAdult(AdultDO adultDO) {
        Optional<AdultBE> optionalAdult = adultRepository.findById(adultDO.getId());

        optionalAdult.ifPresentOrElse(
               adultBE -> {
                   adultBE.setEmail(adultDO.getEmail());
                   adultBE.setFirstName(adultDO.getFirstName());
                   adultBE.setLastName(adultDO.getLastName());
                   adultBE.setEmail(adultDO.getEmail());
                   adultBE.setIsAuthorizedForPickup(adultDO.getIsAuthorizedForPickup());
                   adultBE.setPhoneNumber(adultDO.getPhoneNumber());
                   adultBE.setRelationship(adultDO.getRelationship());
                   adultBE.setPrimary(adultDO.getPrimary());
                   adultBE.setEditedDate(LocalDateTime.now().toString());
                   adultBE.setLastEditedBy("GUESS WHO");
                   adultRepository.save(adultBE);
               },
                () -> {
                    throw new RuntimeException("Allergy not found with id: " + adultDO.getId());
                });
        return new AdultDO(optionalAdult.get());
    }
}
