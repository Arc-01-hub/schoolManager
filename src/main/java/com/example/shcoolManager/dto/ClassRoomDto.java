package com.example.shcoolManager.dto;

import java.util.List;


import lombok.Data;

@Data
public class ClassRoomDto {
    private Long id;
    private String name;
    private List<String> students;
}
