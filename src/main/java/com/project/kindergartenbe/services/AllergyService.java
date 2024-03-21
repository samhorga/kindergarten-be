package com.project.kindergartenbe.services;

import com.project.kindergartenbe.model.be.AllergyBE;
import com.project.kindergartenbe.model.be.StudentBE;
import com.project.kindergartenbe.model.dos.AllergyDO;
import com.project.kindergartenbe.repositories.AllergyRepository;
import com.project.kindergartenbe.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AllergyService {

    private final AllergyRepository allergyRepository;
    private final StudentRepository studentRepository;

//    private final CommonMapper commonMapper;

    public AllergyService(StudentRepository studentRepository, AllergyRepository allergyRepository) {
        this.allergyRepository = allergyRepository;
        this.studentRepository = studentRepository;
    }


    public AllergyDO createAllergy(AllergyDO allergyDO, Long studentID) {
        AllergyBE allergyBE = new AllergyBE(allergyDO);

        allergyBE.setCreatedDate(LocalDateTime.now().toString());
        allergyBE.setEditedDate(LocalDateTime.now().toString());
        allergyBE.setCreatedBy("SAMUEL HORGA");
        allergyBE.setLastEditedBy("SAMUEL HORGA");

        Optional<StudentBE> optionalStudentBE = this.studentRepository.findById(studentID);

        optionalStudentBE.ifPresentOrElse(
                studentBE -> {
                    allergyBE.setStudent(studentBE);
                    this.allergyRepository.save(allergyBE);
                },
                () -> {
                    throw new RuntimeException("Student not found with id: " + studentID);
                });
        return new AllergyDO(allergyBE);
    }

    public void deleteStudentAllergy(Long allergyId) {
        AllergyBE allergyBE = allergyRepository.findById(allergyId).orElseThrow();
        allergyRepository.delete(allergyBE);
    }

    public AllergyDO editAllergy(AllergyDO allergyDO) {
        Optional<AllergyBE> optionalAllergy = allergyRepository.findById(allergyDO.getId());

        optionalAllergy.ifPresentOrElse(
               allergyBE -> {
                   allergyBE.setAllergyName(allergyDO.getAllergyName());
                   allergyBE.setEditedDate(LocalDateTime.now().toString());
                   allergyBE.setLastEditedBy("GUESS WHO");
                   allergyRepository.save(allergyBE);
               },
                () -> {
                    throw new RuntimeException("Allergy not found with id: " + allergyDO.getId());
                });
        return new AllergyDO(optionalAllergy.get());
    }

//    private StudentBE convertToBusinessEntity(StudentBE foundStudent, StudentDO studentDO) {
//        foundStudent.setClassroom(studentDO.getClassroom());
//        foundStudent.setDateOfBirth(studentDO.getDateOfBirth());
//        foundStudent.setFirstName(studentDO.getFirstName());
//        foundStudent.setLastName(studentDO.getLastName());
//        foundStudent.setSchedule(studentDO.getSchedule());
//        foundStudent.setLastEditedBy(studentDO.getLastEditedBy());
//        foundStudent.setEditedDate(studentDO.getEditedDate());
//        foundStudent.setAdults(commonMapper.mapAdultsDOtoAdultsBE(studentDO.getAdults()));
//        foundStudent.setAllergies(commonMapper.mapAllergiesDOtoAllergiesBE(studentDO.getAllergies()));
//        foundStudent.setNotes(commonMapper.mapNotesDOtoNotesBE(studentDO.getNotes()));
//        foundStudent.setVaccines(commonMapper.mapVaccinesDOtoVaccinesBE(studentDO.getVaccines()));
//
//        return foundStudent;
//    }
}
