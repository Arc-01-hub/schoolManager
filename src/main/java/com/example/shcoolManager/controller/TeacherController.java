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

import com.example.shcoolManager.dto.TeacherDto;
import com.example.shcoolManager.model.Teacher;
import com.example.shcoolManager.service.TeacherService;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    @Autowired
    public TeacherService teacherService;

    //GET ALL TEACHERS FOR ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<TeacherDto> getTeachers(){
        return teacherService.getTeachers();
    }

    //GET ONE TEACHERS FOR BOTH ADMIN AND TEACHER
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    @GetMapping("/{id}")
    public TeacherDto getTeacher(@PathVariable Long id){
        return teacherService.getTeacher(id);
    }

    //CREATE TEACHER FOR ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void createTeacher(@RequestBody Teacher teacher){
        teacherService.createTeacher(teacher);
    }

    //UPDATE TEACHER FOR ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
       public void updateTeacher(@RequestBody Teacher teacher,@PathVariable Long id){
        teacherService.updateTeacher(teacher,id);
    }
    //DELETE TEACHER FOR ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
       public void deleteTeacher(@PathVariable Long id){
        teacherService.deleteTeacher(id);
    }

}
