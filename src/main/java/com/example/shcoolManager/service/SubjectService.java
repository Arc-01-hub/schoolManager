package com.example.shcoolManager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shcoolManager.dto.SubjectDto;
import com.example.shcoolManager.model.Subject;
import com.example.shcoolManager.repo.SubjectRepo;

@Service
public class SubjectService {
    @Autowired
    public SubjectRepo subjectRepo;

    
    public SubjectDto converySubjectToDto(Subject subject){
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());
        subjectDto.setTeachersName(subject.getTeachers().stream()
                                                        .map(t->t.getFName()+"_"+t.getLName())
                                                        .collect(Collectors.toList()));
        return subjectDto;
    }
    //GET All SUBJECT
    public List<SubjectDto> getSubjects(){
        return subjectRepo.findAll().stream()
                                    .map(this::converySubjectToDto).collect(Collectors.toList());
    }
    //GET One SUBJECT
    public Subject getSubject(Long id){
        return subjectRepo.findById(id).get();
    }
    //Create  SUBJECT
    public void createSubject(Subject subject){
        subjectRepo.save(subject);
    }
    //Update  Subject
    public void updateSubject(Subject subject,Long id){
        Subject oldSubject = subjectRepo.findById(id).get();
        
        oldSubject.setName(subject.getName());

        subjectRepo.save(oldSubject);
    }
    //DELETE SUBJECT
    public void deleteSubject(Long id){
        Subject oldSubject = subjectRepo.findById(id).get();
        subjectRepo.delete(oldSubject);
    }
}
