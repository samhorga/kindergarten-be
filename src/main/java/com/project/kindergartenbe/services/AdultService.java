package com.project.kindergartenbe.services;

import com.project.kindergartenbe.mappers.CommonMapper;
import com.project.kindergartenbe.model.be.AdultBE;
import com.project.kindergartenbe.model.be.StudentBE;
import com.project.kindergartenbe.model.dos.AdultDO;
import com.project.kindergartenbe.repositories.AdultRepository;
import com.project.kindergartenbe.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
        // Create an instance of AdultBE from AdultDO
        AdultBE adultBE = new AdultBE(adultDO);

        // Set creation and editing metadata
        String now = LocalDateTime.now().toString();
        adultBE.setCreatedDate(now);
        adultBE.setEditedDate(now);
        adultBE.setCreatedBy("SAMUEL HORGA");
        adultBE.setLastEditedBy("SAMUEL HORGA");

        // Find the StudentBE by ID
        Optional<StudentBE> optionalStudentBE = this.studentRepository.findById(studentID);

        // Check if the StudentBE exists
        optionalStudentBE.ifPresentOrElse(
                studentBE -> {
                    // Save the AdultBE
                    this.adultRepository.save(adultBE);

                    // Add the AdultBE to the StudentBE's set of adults
                    studentBE.getAdults().add(adultBE);

                    // Save the updated StudentBE (to update the relationship)
                    studentRepository.save(studentBE);
                },
                () -> {
                    // Throw an exception if the StudentBE is not found
                    throw new RuntimeException("Student not found with id: " + studentID);
                });

        // Return a new AdultDO created from the saved AdultBE
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
                    throw new RuntimeException("Adult not found with id: " + adultDO.getId());
                });
        return new AdultDO(optionalAdult.get());
    }

    public List<AdultDO> retrieveAdults() {
        List<AdultBE> adults = adultRepository.findAll();

        return new CommonMapper().mapAdultsBEtoAdultsDO(adults);
    }
}
