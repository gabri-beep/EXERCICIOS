package com.example.Jpa_02.repository;

import com.example.Jpa_02.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
