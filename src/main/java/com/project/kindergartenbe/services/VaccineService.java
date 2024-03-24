package com.project.kindergartenbe.services;

import com.project.kindergartenbe.model.be.AllergyBE;
import com.project.kindergartenbe.model.be.StudentBE;
import com.project.kindergartenbe.model.be.VaccineBE;
import com.project.kindergartenbe.model.dos.AllergyDO;
import com.project.kindergartenbe.model.dos.VaccineDO;
import com.project.kindergartenbe.repositories.AllergyRepository;
import com.project.kindergartenbe.repositories.StudentRepository;
import com.project.kindergartenbe.repositories.VaccineRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VaccineService {

    private final VaccineRepository vaccineRepository;
    private final StudentRepository studentRepository;

//    private final CommonMapper commonMapper;

    public VaccineService(StudentRepository studentRepository, VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
        this.studentRepository = studentRepository;
    }


    public VaccineDO createVaccine(VaccineDO vaccineDO, Long studentID) {
        VaccineBE vaccineBE = new VaccineBE(vaccineDO);

        vaccineBE.setCreatedDate(LocalDateTime.now().toString());
        vaccineBE.setEditedDate(LocalDateTime.now().toString());
        vaccineBE.setCreatedBy("SAMUEL HORGA");
        vaccineBE.setLastEditedBy("SAMUEL HORGA");

        Optional<StudentBE> optionalStudentBE = this.studentRepository.findById(studentID);

        optionalStudentBE.ifPresentOrElse(
                studentBE -> {
                    vaccineBE.setStudent(studentBE);
                    this.vaccineRepository.save(vaccineBE);
                },
                () -> {
                    throw new RuntimeException("Student not found with id: " + studentID);
                });
        return new VaccineDO(vaccineBE);
    }

//    public void deleteStudentAllergy(Long allergyId) {
//        AllergyBE allergyBE = allergyRepository.findById(allergyId).orElseThrow();
//        allergyRepository.delete(allergyBE);
//    }
//
//    public AllergyDO editAllergy(AllergyDO allergyDO) {
//        Optional<AllergyBE> optionalAllergy = allergyRepository.findById(allergyDO.getId());
//
//        optionalAllergy.ifPresentOrElse(
//               allergyBE -> {
//                   allergyBE.setAllergyName(allergyDO.getAllergyName());
//                   allergyBE.setEditedDate(LocalDateTime.now().toString());
//                   allergyBE.setLastEditedBy("GUESS WHO");
//                   allergyRepository.save(allergyBE);
//               },
//                () -> {
//                    throw new RuntimeException("Allergy not found with id: " + allergyDO.getId());
//                });
//        return new AllergyDO(optionalAllergy.get());
//    }

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
