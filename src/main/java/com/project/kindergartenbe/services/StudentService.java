package com.project.kindergartenbe.services;

import com.project.kindergartenbe.mappers.CommonMapper;
import com.project.kindergartenbe.model.be.StudentBE;
import com.project.kindergartenbe.model.dos.StudentDO;
import com.project.kindergartenbe.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final CommonMapper commonMapper;

    public StudentService(StudentRepository studentRepository, CommonMapper commonMapper) {
        this.studentRepository = studentRepository;
        this.commonMapper = commonMapper;
    }


    public StudentDO createStudent(StudentDO studentDO) {
        StudentBE studentBE = new StudentBE(studentDO);

        studentBE.setCreatedDate(LocalDate.now().toString());
        studentBE.setEditedDate(LocalDate.now().toString());
        studentBE.setCreatedBy("SAMUEL HORGA");
        studentBE.setLastEditedBy("SAMUEL HORGA");
        this.studentRepository.save(studentBE);

        return new StudentDO(studentBE);
    }

    public List<StudentDO> retrieveStudents() {
        List<StudentBE> foundStudents = this.studentRepository.findAll();
        List<StudentDO> studentListTOBeReturned = new ArrayList<>();

        foundStudents.forEach(studentBE -> studentListTOBeReturned.add(new StudentDO(studentBE)));

        return studentListTOBeReturned;
    }

    public void deleteStudent(Long id) {
        StudentBE studentBETobeDeleted = studentRepository.findById(id).orElseThrow();

        studentRepository.delete(studentBETobeDeleted);
    }

    public StudentDO editStudent(StudentDO studentDO) {
        StudentBE foundStudent = studentRepository.findById(studentDO.getId()).orElseThrow();

        StudentBE updatedStudentBE = convertToBusinessEntity(foundStudent, studentDO);

        return new StudentDO(studentRepository.save(updatedStudentBE));
    }

    private StudentBE convertToBusinessEntity(StudentBE foundStudent, StudentDO studentDO) {
        foundStudent.setClassroom(studentDO.getClassroom());
        foundStudent.setDateOfBirth(studentDO.getDateOfBirth());
        foundStudent.setFirstName(studentDO.getFirstName());
        foundStudent.setLastName(studentDO.getLastName());
        foundStudent.setSchedule(studentDO.getSchedule());
        foundStudent.setLastEditedBy(studentDO.getLastEditedBy());
        foundStudent.setEditedDate(studentDO.getEditedDate());
//        foundStudent.setAdults(commonMapper.mapAdultsDOtoAdultsBE(studentDO.getAdults()));
//        foundStudent.setAllergies(commonMapper.mapAllergiesDOtoAllergiesBE(studentDO.getAllergies()));
//        foundStudent.setNotes(commonMapper.mapNotesDOtoNotesBE(studentDO.getNotes()));
//        foundStudent.setVaccines(commonMapper.mapVaccinesDOtoVaccinesBE(studentDO.getVaccines()));

        return foundStudent;
    }

    public List<StudentDO> retrieveStudentsByAdultId(String adultId) {
        List<StudentBE> studentBES = this.studentRepository.findStudentsByAdultId(Long.valueOf(adultId));
        return studentBES.stream().map(StudentDO::new).collect(Collectors.toList());
    }
}
