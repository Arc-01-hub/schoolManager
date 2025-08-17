package com.example.shcoolManager.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shcoolManager.repo.StudentRepo;
import com.example.shcoolManager.repo.TeacherRepo;

@Service
public class AdminService {
    @Autowired
    public TeacherRepo teacherRepo;
    @Autowired
    public StudentRepo studentRepo;

    //Get all teachers for admin
}
