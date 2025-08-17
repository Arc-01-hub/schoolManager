package com.example.shcoolManager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shcoolManager.dto.TeacherDto;
import com.example.shcoolManager.model.Teacher;
import com.example.shcoolManager.model.User;
import com.example.shcoolManager.repo.TeacherRepo;
import com.example.shcoolManager.repo.UserRepo;

@Service
public class TeacherService {
    @Autowired
    public TeacherRepo teacherRepo;
    @Autowired
    public UserRepo userRepo;
    @Autowired
    public AuthService authService;
    @Autowired
    public PasswordEncoder passwordEncoder;

    public TeacherDto converyTeacherToDto(Teacher teacher){
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setFName(teacher.getFName());
        teacherDto.setLName(teacher.getLName());
        teacherDto.setPassword(teacher.getPassword());
        teacherDto.setAge(teacher.getAge()); 
        teacherDto.setSubject(teacher.getSubject ());
        return teacherDto;
    }
    //GET All TEACHERS
    public List<TeacherDto> getTeachers(){
        return teacherRepo.findAll().stream()
                                    .map(this::converyTeacherToDto).collect(Collectors.toList());
    }
    //GET One TEACHER
    public TeacherDto getTeacher(Long id){
        return converyTeacherToDto(teacherRepo.findById(id).get());
    }
    //Create  TEACHER
    public void createTeacher(Teacher teacher){
        User newUser = new User();
        newUser.setUserName(teacher.getFName());
        newUser.setPassword(teacher.getPassword());
        newUser.setType("teacher");
        User savedUser = authService.Register(newUser);
        teacher.setUser(savedUser);
        teacher.setPassword(null);

        teacherRepo.save(teacher);
    }
    //Update  TEACHER
    public void updateTeacher(Teacher teacher,Long id){
        Teacher oldTeacher = teacherRepo.findById(id).get();
        User oldUser = oldTeacher.getUser();
        
        oldTeacher.setFName(teacher.getFName());
        oldUser.setUserName(teacher.getFName());
        oldTeacher.setLName(teacher.getLName());
        oldTeacher.setAge(teacher.getAge());
        oldTeacher.setSubject(teacher.getSubject());
        if (teacher.getPassword() != null && !teacher.getPassword().isBlank()) {
            String hashedPassword = passwordEncoder.encode(teacher.getPassword());
            oldTeacher.setPassword(null);
            oldUser.setPassword(hashedPassword);
        }
        userRepo.save(oldUser);
        teacherRepo.save(oldTeacher);
    }
    //DELETE TEACHER
    public void deleteTeacher(Long id){
        Teacher oldTeacher = teacherRepo.findById(id).get();
        User relatedUser =oldTeacher.getUser();

        if(relatedUser != null){
            oldTeacher.setUser(null);
            teacherRepo.save(oldTeacher);
            userRepo.delete(relatedUser);  
        }

        teacherRepo.delete(oldTeacher);
    }
}
