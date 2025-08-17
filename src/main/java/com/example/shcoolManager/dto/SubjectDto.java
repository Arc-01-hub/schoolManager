package com.example.shcoolManager.dto;

import java.util.List;

import lombok.Data;

@Data
public class SubjectDto {
    private Long id;
    private String name;
    private List<String> teachersName;
}
