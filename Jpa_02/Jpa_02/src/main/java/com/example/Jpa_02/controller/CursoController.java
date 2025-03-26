package com.example.Jpa_02.controller;

import com.example.Jpa_02.entity.Curso;
import com.example.Jpa_02.entity.Professor;
import com.example.Jpa_02.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<Curso> created(@RequestBody Curso curso){
        Curso cursoBd = cursoRepository.save(curso);
        return ResponseEntity.ok(curso);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> getAll(){
        List<Curso> cursos = cursoRepository.findAll();

        if (cursos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return  ResponseEntity.status(HttpStatus.OK).body(cursos);
        }
    }

    @GetMapping("/{idCurso}")
    public ResponseEntity<Curso> getById(@PathVariable Long idCurso){
        Optional<Curso> cursoOptional = cursoRepository.findById(idCurso);
        if (cursoOptional.isPresent()){
            Curso curso = cursoOptional.get();
            return ResponseEntity.ok(curso);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{idCurso}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Curso updateCurso){
        Optional<Curso> cursoOptional = cursoRepository.findById(id);

        if (cursoOptional.isPresent()){
            Curso curso = cursoOptional.get();
            curso.setNome(updateCurso.getNome());
            curso.setNumeroSala(updateCurso.getNumeroSala());
            curso.setProfessor(updateCurso.getProfessor());
            return ResponseEntity.ok(cursoRepository.save(curso));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
        }
    }

    @DeleteMapping("/{idCurso}")
    public ResponseEntity<String> delete(@PathVariable Long idCurso){
        Optional<Curso> optionalCurso = cursoRepository.findById(idCurso);

        if (optionalCurso.isPresent()){
            Curso curso = optionalCurso.get();
            cursoRepository.delete(curso);
            return ResponseEntity.ok("Curso deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado!");
        }
    }
}
