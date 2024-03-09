package com.project.kindergartenbe.services;

import com.project.kindergartenbe.mappers.CommonMapper;
import com.project.kindergartenbe.model.be.NoteBE;
import com.project.kindergartenbe.model.be.StudentBE;
import com.project.kindergartenbe.model.dos.NoteDO;
import com.project.kindergartenbe.model.dos.StudentDO;
import com.project.kindergartenbe.repositories.NoteRepository;
import com.project.kindergartenbe.repositories.StudentRepository;
import com.project.kindergartenbe.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final StudentRepository studentRepository;

//    private final CommonMapper commonMapper;

    public NoteService(StudentRepository studentRepository, NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
        this.studentRepository = studentRepository;
    }


    public void createNote(NoteDO noteDO, Long studentID) {
        NoteBE noteBE = new NoteBE(noteDO);

        noteBE.setCreatedDate(LocalDate.now().toString());
        noteBE.setEditedDate(LocalDate.now().toString());
        noteBE.setCreatedBy("SAMUEL HORGA");
        noteBE.setLastEditedBy("SAMUEL HORGA");

        Optional<StudentBE> optionalStudentBE = this.studentRepository.findById(studentID);

        optionalStudentBE.ifPresentOrElse(
                studentBE -> {
                    noteBE.setStudent(studentBE);
                    this.noteRepository.save(noteBE);
                },
                () -> {
                    throw new RuntimeException("Student not found with id: " + studentID);
                });
    }

    public List<StudentDO> retrieveStudentNotes(Long studentId) {
        List<StudentBE> foundStudents = this.studentRepository.findAll();
        List<StudentDO> studentListTOBeReturned = new ArrayList<>();

        foundStudents.forEach(studentBE -> studentListTOBeReturned.add(new StudentDO(studentBE)));

        return studentListTOBeReturned;
    }

    public void deleteStudentNote(Long studentId, Long note) {
//        StudentBE studentBETobeDeleted = studentRepository.findById(id).orElseThrow();

//        studentRepository.delete(studentBETobeDeleted);
    }

    public StudentDO editNote(Long noteId) {
//        StudentBE foundStudent = studentRepository.findById(studentDO.getId()).orElseThrow();
//
//        StudentBE updatedStudentBE = convertToBusinessEntity(foundStudent, studentDO);
//
//        return new StudentDO(studentRepository.save(updatedStudentBE));

        return null;
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
