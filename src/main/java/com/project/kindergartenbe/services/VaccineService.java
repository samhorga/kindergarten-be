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

    public void deleteStudentVaccine(Long vaccineID) {
        VaccineBE vaccineBE = vaccineRepository.findById(vaccineID).orElseThrow();
        vaccineRepository.delete(vaccineBE);
    }

    public VaccineDO editVaccine(VaccineDO vaccineDO) {
        Optional<VaccineBE> optionalVaccine = vaccineRepository.findById(vaccineDO.getId());

        optionalVaccine.ifPresentOrElse(
                vaccineBE -> {
                    vaccineBE.setVaccineName(vaccineDO.getVaccineName());
                    vaccineBE.setVaccineDoses(vaccineDO.getVaccineDoses());
                    vaccineBE.setVaccineDate(vaccineDO.getVaccineDate());
                    vaccineBE.setEditedDate(LocalDateTime.now().toString());
                    vaccineBE.setLastEditedBy("GUESS WHO");
                    vaccineRepository.save(vaccineBE);
                },
                () -> {
                    throw new RuntimeException("Vaccine not found with id: " + vaccineDO.getId());
                });
        return new VaccineDO(optionalVaccine.get());
    }
}
