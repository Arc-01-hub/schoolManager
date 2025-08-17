package com.example.shcoolManager.controller;

import com.example.shcoolManager.dto.StudentDto;
import com.example.shcoolManager.model.Student;
import com.example.shcoolManager.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // CREATE Student
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void createStudent(@RequestBody StudentDto studentDto) {
        studentService.createStudent(studentDto);
    }

    // GET ALL Students
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<StudentDto> getStudents() {
        return studentService.getStudents();
    }

    // GET ONE Student
    @PreAuthorize("hasRole('ADMIN','STUDENT')")
    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    // UPDATE Student
    // @PreAuthorize("hasRole('ADMIN')")
    // @PutMapping("/{id}")
    // public void updateStudent(@PathVariable Long id, @RequestBody Student student) {
    //     studentService.updateStudent(student, id);
    // }

    // DELETE Student
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
