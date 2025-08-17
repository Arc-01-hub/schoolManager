package com.example.shcoolManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.shcoolManager.dto.ClassRoomDto;
import com.example.shcoolManager.model.ClassRoom;
import com.example.shcoolManager.service.ClassRoomService;

@RestController
@RequestMapping("/api/classrooms")
public class ClassRoomController {

    @Autowired
    private ClassRoomService classRoomService;

    // GET ALL ClassRooms (Admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<ClassRoomDto> getClassRooms() {
        return classRoomService.getClassRooms();
    }

    // @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    // @GetMapping("/{id}")
    // public ClassRoomDto getClassRoom(@PathVariable Long id) {
    //     ClassRoom classRoom = classRoomService.getClassRoom(id);
    //     return classRoomService.convertClassRoomToDto(classRoom);
    // }

    // CREATE ClassRoom (Admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void createClassRoom(@RequestBody ClassRoom classRoom) {
        classRoomService.createClassRoom(classRoom);
    }

    // UPDATE ClassRoom (Admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public void updateClassRoom(@RequestBody ClassRoom classRoom, @PathVariable Long id) {
        classRoomService.updateClassRoom(classRoom, id);
    }

    // DELETE ClassRoom (Admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteClassRoom(@PathVariable Long id) {
        classRoomService.deleteClassRoom(id);
    }
}
