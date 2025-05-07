package com.example.Escola_Back_End.Repository;

import com.example.Escola_Back_End.Entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findAllByNomeProfessor(String nomeProfessor);
}
//davi-30/04