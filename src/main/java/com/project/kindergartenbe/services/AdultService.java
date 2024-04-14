package com.project.kindergartenbe.services;

import com.project.kindergartenbe.mappers.CommonMapper;
import com.project.kindergartenbe.model.be.AdultBE;
import com.project.kindergartenbe.model.be.AdultStudentBE;
import com.project.kindergartenbe.model.be.StudentBE;
import com.project.kindergartenbe.model.dos.*;
import com.project.kindergartenbe.repositories.AdultRepository;
import com.project.kindergartenbe.repositories.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdultService {

    private final AdultRepository adultRepository;
    private final StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager entityManager;

//    private final CommonMapper commonMapper;

    public AdultService(StudentRepository studentRepository, AdultRepository adultRepository) {
        this.adultRepository = adultRepository;
        this.studentRepository = studentRepository;
    }


    public AdultDO createAdult(AdultDO adultDO, String studentID) {
        Optional<List<AdultBE>> adultBEOptional = adultRepository.tryToFindAdultByFirstNameLastName(adultDO.getFirstName(),
                adultDO.getLastName(), adultDO.getRelationship(), adultDO.getPhoneNumber());

        if (adultBEOptional.isPresent() && adultBEOptional.get().size() == 1 && Objects.nonNull(studentID) && !studentID.equalsIgnoreCase("notStudentId")) {
            Long adultId = adultBEOptional.get().get(0).getId();
            long parsedStudentId = Long.parseLong(studentID);

            String createConstraintAdultStudent = "INSERT INTO adult_student (adult_id, student_id) VALUES (:adultId, :studentId)";
            Query query = entityManager.createNativeQuery(createConstraintAdultStudent);
            query.setParameter("adultId", adultId);
            query.setParameter("studentId", parsedStudentId);

            query.executeUpdate();
                return new AdultDO(adultBEOptional.get().get(0));
        } else {
            AdultBE adultBE = new AdultBE(adultDO);

            String now = LocalDateTime.now().toString();
            adultBE.setCreatedDate(now);
            adultBE.setEditedDate(now);
            adultBE.setCreatedBy("SAMUEL HORGA");
            adultBE.setLastEditedBy("SAMUEL HORGA");

            // Find the StudentBE by ID
            if (!studentID.equalsIgnoreCase("notStudentId")) {
                Optional<StudentBE> optionalStudentBE = this.studentRepository.findById(Long.valueOf(studentID));

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
            } else {
                //NEW ADULT CREATION, NOT THROUGH CHILD
                this.adultRepository.save(adultBE);
            }

            // Return a new AdultDO created from the saved AdultBE
            return new AdultDO(adultBE);
        }
    }

    public void deleteAdult(Long adultId) {
        String dropConstraintQuery = "DELETE FROM adult_student WHERE adult_id = "+adultId;
        entityManager.createNativeQuery(dropConstraintQuery).executeUpdate();

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
        List<AdultBE> adultsBEs = adultRepository.findAll();
       // adults.forEach(adultBE -> adultBE.setStudents(studentRepository.findStudentsByAdultId(adultBE.getId())));
        List<AdultDO> adultDOList = new CommonMapper().mapAdultsBEtoAdultsDO(adultsBEs);

        for(AdultBE adultBE: adultsBEs) {
            for(StudentBE studentBE: adultBE.getStudents()) {
                for(AdultDO adultDO : adultDOList) {
                        if(adultBE.getId()==adultDO.getId()){
                            StudentDO studentDO = new StudentDO();

                            studentDO.setCreatedBy(studentBE.getCreatedBy());
                            studentDO.setClassroom(studentBE.getClassroom());
                            studentDO.setDateOfBirth(studentBE.getDateOfBirth());
                            studentDO.setFirstName(studentBE.getFirstName());
                            studentDO.setLastName(studentBE.getLastName());
                            studentDO.setSchedule(studentBE.getSchedule());
                            studentDO.setCreatedDate(studentBE.getCreatedDate());
                            studentDO.setLastEditedBy(studentBE.getLastEditedBy());
                            studentDO.setEditedDate(studentBE.getEditedDate());
                            studentDO.setAllergies(studentBE.getAllergies().stream().map(AllergyDO::new).collect(Collectors.toList()));
                            studentDO.setNotes(studentBE.getNotes().stream().map(NoteDO::new).collect(Collectors.toList()));
                            studentDO.setVaccines(studentBE.getVaccines().stream().map(VaccineDO::new).collect(Collectors.toSet()));

                            adultDO.getStudents().add(studentDO);
                        }
                    }
            }
        }
        return adultDOList;
    }
}
