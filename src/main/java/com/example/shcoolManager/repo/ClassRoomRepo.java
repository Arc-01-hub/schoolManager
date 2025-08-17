package com.example.shcoolManager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shcoolManager.model.ClassRoom;

@Repository
public interface ClassRoomRepo extends JpaRepository<ClassRoom,Long>{
    
}
