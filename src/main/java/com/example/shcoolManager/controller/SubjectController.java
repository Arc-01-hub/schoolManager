package com.example.shcoolManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shcoolManager.dto.SubjectDto;
import com.example.shcoolManager.model.Subject;
import com.example.shcoolManager.service.SubjectService;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired
    public SubjectService subjectService;

    //GET ALL Subjects FOR ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<SubjectDto> getSubjects(){
        return subjectService.getSubjects();
    }

    // //GET ONE Subject FOR BOTH ADMIN AND TEACHER
    // @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    // @GetMapping("/{id}")
    // public TeacherDto getTeacher(@PathVariable Long id){
    //     return subjectService.getTeacher(id);
    // }

    //Add Subject FOR ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void createSubject(@RequestBody Subject subject){
        subjectService.createSubject(subject);
    }

    //UPDATE Subject FOR ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
       public void updateSubject(@RequestBody Subject subject,@PathVariable Long id){
        subjectService.updateSubject(subject,id);
    }
    //Delete Subject FOR ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
       public void deleteSubject(@PathVariable Long id){
        subjectService.deleteSubject(id);
    }
}
