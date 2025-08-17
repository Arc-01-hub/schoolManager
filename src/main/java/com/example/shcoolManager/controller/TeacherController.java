package com.example.shcoolManager.controller;

import com.example.shcoolManager.dto.TeacherDto;
import com.example.shcoolManager.model.Teacher;
import com.example.shcoolManager.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // CREATE
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void createTeacher(@RequestBody TeacherDto teacherdto) {
        teacherService.createTeacher(teacherdto);
    }

    // get ALL
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<TeacherDto> getTeachers() {
        return teacherService.getTeachers();
    }

    // get ONE
    @PreAuthorize("hasRole('ADMIN','TEACHER')")
    @GetMapping("/{id}")
    public TeacherDto getTeacher(@PathVariable Long id) {
        return teacherService.getTeacher(id);
    }

    // UPDATE
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public void updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        teacherService.updateTeacher(teacher,id);
    }

    // DELETE
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }
}
