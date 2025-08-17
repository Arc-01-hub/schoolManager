package com.example.shcoolManager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shcoolManager.dto.TeacherDto;
import com.example.shcoolManager.model.Subject;
import com.example.shcoolManager.model.Teacher;
import com.example.shcoolManager.model.User;
import com.example.shcoolManager.repo.SubjectRepo;
import com.example.shcoolManager.repo.TeacherRepo;
import com.example.shcoolManager.repo.UserRepo;

@Service
public class TeacherService {
    @Autowired
    public TeacherRepo teacherRepo;
    @Autowired
    public UserRepo userRepo;
    @Autowired
    public SubjectRepo subjectRepo;
    @Autowired
    public AuthService authService;
    @Autowired
    public PasswordEncoder passwordEncoder;

    // Dto to teacher
    public Teacher converyDtoToTeacher(TeacherDto teacherdto){
        Teacher teacher = new Teacher();
        teacher.setId(teacherdto.getId());
        teacher.setFName(teacherdto.getFName());
        teacher.setLName(teacherdto.getLName());
        teacher.setPassword(null);
        teacher.setAge(teacherdto.getAge()); 
        teacher.setSubject(subjectRepo.findById(teacherdto.getSubjectId()).get());
        return teacher;
    }
    // TEACHER TO DTO
    public TeacherDto converyTeacherToDto(Teacher teacher){
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setFName(teacher.getFName());
        teacherDto.setLName(teacher.getLName());
        teacherDto.setPassword(teacher.getPassword());
        teacherDto.setAge(teacher.getAge()); 
        teacherDto.setSubjectId(teacher.getSubject ().getId());
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
    public void createTeacher(TeacherDto teacherDto){
        User newUser = new User();
        newUser.setUserName(teacherDto.getFName());
        newUser.setPassword(teacherDto.getPassword());
        newUser.setType("teacher");

        User savedUser = authService.Register(newUser);

        Teacher teacher =converyDtoToTeacher(teacherDto);
        teacher.setUser(savedUser);

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
