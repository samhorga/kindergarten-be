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


    public NoteDO createNote(NoteDO noteDO, Long studentID) {
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
        return new NoteDO(noteBE);
    }

    public void deleteStudentNote(Long note) {
        NoteBE noteBE = noteRepository.findById(note).orElseThrow();
        noteRepository.delete(noteBE);
    }

    public NoteDO editNote(NoteDO noteDO) {
        Optional<NoteBE> optionalNote = noteRepository.findById(noteDO.getId());

        optionalNote.ifPresentOrElse(
               noteBE -> {
                   noteBE.setNote(noteDO.getNote());
                   noteBE.setEditedDate(LocalDate.now().toString());
                   noteBE.setLastEditedBy("GUESS WHO");
                   noteRepository.save(noteBE);
               },
                () -> {
                    throw new RuntimeException("Note not found with id: " + noteDO.getId());
                });
        return new NoteDO(optionalNote.get());
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
