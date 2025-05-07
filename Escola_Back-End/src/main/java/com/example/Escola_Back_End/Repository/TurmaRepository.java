package com.example.Escola_Back_End.Repository;

import com.example.Escola_Back_End.Entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    List<Turma> getAllByNomeTurma(String nome);
}
