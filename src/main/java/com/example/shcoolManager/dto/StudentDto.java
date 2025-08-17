package com.example.shcoolManager.dto;

import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String fName;
    private String lName;
    private String password;
    private int age;
    private Long classRoomId;
    // private String classRoomName;
}
