package com.example.Jpa_02.repository;

import com.example.Jpa_02.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
