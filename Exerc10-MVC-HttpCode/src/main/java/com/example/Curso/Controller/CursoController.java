package com.example.Curso.Controller;

import com.example.Curso.banco.CursoDb;
import com.example.Curso.model.Aluno;
import com.example.Curso.model.Curso;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CursoController {

    CursoDb repository = new CursoDb();

    public ResponseEntity<List<Curso>> getAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<List<Curso>> getByProfessor(String nomeProfessor){
        if (nomeProfessor != null){
            return ResponseEntity.ok(repository.findByProfessor(nomeProfessor));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<List<Curso>> getBySala(int sala){
        if (sala > 0){
            return ResponseEntity.ok(repository.findBySala(sala));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Curso> getById(int id){
        Curso curso = repository.getById(id);
        if (curso != null){
            return ResponseEntity.ok(curso);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Curso> insertBanco(Curso curso){
        Curso cursoSave = repository.insert(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoSave);
    }

    /*public Curso insertAluno(int idCurso, Aluno aluno){
        return repository.insertAluno(idCurso, aluno);
    }*/

    // esta funcao fara o mesmo insert do aluno que a funcao a cima, porém com melhores práticas de programção
    public ResponseEntity<Curso> insertAlunoMelhorado(int idCurso, Aluno aluno){
        Curso curso = repository.getById(idCurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.insertAlunoMelhorado(curso, aluno));
    }

    public ResponseEntity<Curso> update(int id, Curso curso){
        Curso cursoBd = repository.getById(id);
        if (cursoBd == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        repository.update(id, curso);
        return ResponseEntity.ok(curso);
    }

    public ResponseEntity<String> updateAluno(int idCurso, int idAluno, Aluno aluno){
        boolean cursoBd = repository.updateAluno(idCurso, idAluno, aluno);
        if (cursoBd = false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno com ID: "+ idAluno + "não encontrado no curso");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Aluno atualizado com sucesso");
        }
    }

    public ResponseEntity<String> delete(int id){
        Curso cursoBd = repository.getById(id);
        if (cursoBd == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso com ID: " + id + " não encontrado");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Curso " + cursoBd.getNome() + " deletado com sucesso");
        }
    }

}
