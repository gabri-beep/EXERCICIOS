package com.example.Jpa_02.controller;

import com.example.Jpa_02.entity.Professor;
import com.example.Jpa_02.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping
    public ResponseEntity<Professor> created(@RequestBody Professor professor){
        Professor professorBd = professorRepository.save(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(professor);
    }

    @GetMapping
    public ResponseEntity<List<Professor>> getAll(){
        List<Professor> professores = professorRepository.findAll();

        if (professores.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return  ResponseEntity.status(HttpStatus.OK).body(professores);
        }
    }

    @GetMapping("/{idProfessor}")
    public ResponseEntity<Professor> getById(@PathVariable Long id){
        Optional<Professor> professorOptional = professorRepository.findById(id);

        if (professorOptional.isPresent()) {
            Professor professor = professorOptional.get();
            return ResponseEntity.status(HttpStatus.FOUND).body(professor);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PutMapping("/{idProfessor}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Professor updateProfessor){
        Optional<Professor> optionalProfessor = professorRepository.findById(id);

        if (optionalProfessor.isPresent()){
            Professor professor = optionalProfessor.get();
            professor.setNome(updateProfessor.getNome());
            professor.setCpf(updateProfessor.getCpf());
             return ResponseEntity.ok(professorRepository.save(professor));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado");
        }
    }

    @DeleteMapping("/{idProfessor}")
    public ResponseEntity<String> delete(@PathVariable Long idProfessor){
        Optional<Professor> optionalProfessor = professorRepository.findById(idProfessor);

        if (optionalProfessor.isPresent()){
            Professor professor = optionalProfessor.get();
            professorRepository.delete(professor);
            return ResponseEntity.ok("Professor deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado!");
        }
    }

}
