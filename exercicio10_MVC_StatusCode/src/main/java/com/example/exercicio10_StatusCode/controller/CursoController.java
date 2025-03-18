package com.example.exercicio10_StatusCode.controller;

import com.example.exercicio10_StatusCode.banco.CursoBd;
import com.example.exercicio10_StatusCode.model.Curso;
import com.example.exercicio10_StatusCode.model.Aluno;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CursoController {
    CursoBd repository = new CursoBd();


    public ResponseEntity<List<Curso>> getAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<Curso> getByIdCurso(Long idCurso) {
        Curso curso = repository.getByIdCurso(idCurso);
        if (curso != null){
            return ResponseEntity.ok(curso); //código 200(sucesso)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); //código 404(págin não encontrada)
        }
    }
    public ResponseEntity<Curso> getByNumeroSala(Integer numeroSala){
        if(numeroSala != null){
            return ResponseEntity.ok(repository.getByNumeroSala(numeroSala));
        } else {
            return ResponseEntity.ok(repository.getByNumeroSala(numeroSala));
        }
    }

    public Curso getByNomeProfessor(String nomeProfessor){
        return repository.getByNomeProfessor(nomeProfessor);
    }

    public ResponseEntity<Curso> insertBanco(Curso curso){
        Curso cursoSave = repository.insert(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoSave);
    }

    public Curso insertAluno(Long idCurso, Aluno aluno){
        Curso curso = repository.getByIdCurso(idCurso);
        return repository.insertAluno(curso, aluno);
    }

    public  Curso update(Long idCurso, Curso curso ){
        boolean result = repository.update(idCurso, curso);

        if(result){
            return curso;
        }
        return null;
    }

    public boolean updateAluno(int idCurso, int idAluno, Aluno aluno){
        return repository.updateAluno(idCurso, idAluno, aluno);
    }


    public boolean delete(Long idCurso){
        return repository.delete(idCurso);


    }

}
