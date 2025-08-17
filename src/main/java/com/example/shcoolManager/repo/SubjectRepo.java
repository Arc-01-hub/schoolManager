package com.example.shcoolManager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shcoolManager.model.Subject;

@Repository
public interface SubjectRepo extends JpaRepository<Subject,Long> {
    
}
