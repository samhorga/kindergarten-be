package com.project.kindergartenbe.services;

import com.project.kindergartenbe.model.be.StudentBE;
import com.project.kindergartenbe.model.dos.StudentDO;
import com.project.kindergartenbe.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(StudentDO studentDO) {
        StudentBE studentBE = new StudentBE(studentDO);

        studentBE.setCreatedDate(LocalDate.now().toString());
        studentBE.setEditedDate(LocalDate.now().toString());
        studentBE.setCreatedBy("SAMUEL HORGA");
        studentBE.setLastEditedBy("SAMUEL HORGA");
        this.studentRepository.save(studentBE);
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
}
