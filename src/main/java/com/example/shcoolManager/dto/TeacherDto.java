package com.example.shcoolManager.dto;

import com.example.shcoolManager.model.Subject;

import lombok.Data;

@Data
public class TeacherDto {
    private Long id;
    private String fName;
    private String lName;
    private String password;
    private int age;
    private Subject subject;
}