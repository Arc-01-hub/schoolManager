package com.example.shcoolManager.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shcoolManager.dto.StudentDto;
import com.example.shcoolManager.model.Student;
import com.example.shcoolManager.model.User;
import com.example.shcoolManager.repo.ClassRoomRepo;
import com.example.shcoolManager.repo.StudentRepo;

@Service
public class StudentService {
    @Autowired
    public StudentRepo studentRepo;
    @Autowired
    public ClassRoomRepo classRoomRepo;
    
    @Autowired
    public AuthService authService;

    // convert dto to Student
    public Student converyDtoToStudent(StudentDto studentDto){
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setFName(studentDto.getFName());
        student.setLName(studentDto.getLName());
        student.setPassword(null);
        student.setAge(studentDto.getAge()); 
        student.setClassRoom(classRoomRepo.findById(studentDto.getClassRoomId()).get());
        return student;
    }
    // convert Student to dto
    public StudentDto converyStudentToDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFName(student.getFName());
        studentDto.setLName(student.getLName());
        studentDto.setAge(student.getAge()); 
        studentDto.setClassRoomId(student.getClassRoom().getId());
        return studentDto;
    }
    //Get all Students 
    public List<StudentDto> getStudents(){
        return studentRepo.findAll().stream()
                                    .map(this::converyStudentToDto).collect(Collectors.toList());
    }
    //Get one Student
    public StudentDto getStudent(Long id){
        return converyStudentToDto(studentRepo.findById(id).get());
    }
    //Create  Student
    public void createStudent(StudentDto studentDto){
        User newUser = new User();
        newUser.setUserName(studentDto.getFName());
        newUser.setPassword(studentDto.getPassword());
        newUser.setType("student");

        User savedUser = authService.Register(newUser);

        Student student = converyDtoToStudent(studentDto);
        student.setUser(savedUser);

        studentRepo.save(student);
    }
    //Delete one Student
    public void deleteStudent(Long id){
        studentRepo.deleteById(id);
    }
}
