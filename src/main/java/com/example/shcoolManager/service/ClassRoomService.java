package com.example.shcoolManager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shcoolManager.dto.ClassRoomDto;
import com.example.shcoolManager.model.ClassRoom;
import com.example.shcoolManager.repo.ClassRoomRepo;

@Service
public class ClassRoomService {

    @Autowired
    private ClassRoomRepo classRoomRepo;

    // تحويل ClassRoom → ClassRoomDto
    public ClassRoomDto convertClassRoomToDto(ClassRoom classRoom){
        ClassRoomDto classRoomDto = new ClassRoomDto();
        classRoomDto.setId(classRoom.getId());
        classRoomDto.setName(classRoom.getName());
        classRoomDto.setStudents(classRoom.getStudents().stream()
                                                        .map(s->s.getFName()+"_"+s.getLName())
                                                        .collect(Collectors.toList()));
        return classRoomDto;
    }

    // GET All ClassRooms
    public List<ClassRoomDto> getClassRooms(){
        return classRoomRepo.findAll().stream()
                            .map(this::convertClassRoomToDto)
                            .collect(Collectors.toList());
    }

    // GET One ClassRoom
    public ClassRoom getClassRoom(Long id){
        return classRoomRepo.findById(id)
                            .orElseThrow(() -> new RuntimeException("ClassRoom not found"));
    }

    // Create ClassRoom
    public void createClassRoom(ClassRoom classRoom){
        classRoomRepo.save(classRoom);
    }

    // Update ClassRoom
    public void updateClassRoom(ClassRoom classRoom, Long id){
        ClassRoom oldClassRoom = classRoomRepo.findById(id)
                            .orElseThrow(() -> new RuntimeException("ClassRoom not found"));
        oldClassRoom.setName(classRoom.getName());
        classRoomRepo.save(oldClassRoom);
    }

    // Delete ClassRoom
    public void deleteClassRoom(Long id){
        ClassRoom classRoom = classRoomRepo.findById(id)
                            .orElseThrow(() -> new RuntimeException("ClassRoom not found"));
        classRoomRepo.delete(classRoom);
    }
}
