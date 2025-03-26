package com.example.Jpa_02.controller;

import com.example.Jpa_02.entity.Aluno;
import com.example.Jpa_02.entity.Professor;
import com.example.Jpa_02.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    public ResponseEntity<Aluno> created(@RequestBody Aluno aluno){
        Aluno alunoBd = alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll(){
        return ResponseEntity.ok(alunoRepository.findAll());
    }

    @GetMapping("/{idAluno}")
    public ResponseEntity<Aluno> getById(@PathVariable Long idAluno){
        Optional<Aluno> alunoOptional = alunoRepository.findById(idAluno);

        if (alunoOptional.isPresent()){
            Aluno aluno = alunoOptional.get();
            return ResponseEntity.status(HttpStatus.FOUND).body(aluno);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PutMapping("/{idAluno}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Aluno updateAluno){
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()){
            Aluno aluno = optionalAluno.get();
            aluno.setNome(updateAluno.getNome());
            aluno.setCpf(updateAluno.getCpf());
            return ResponseEntity.ok(alunoRepository.save(aluno)) ;
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
    }

    @DeleteMapping("/{idAluno}")
    public ResponseEntity<String> delete(@PathVariable Long idAluno){
        Optional<Aluno> optionalAluno = alunoRepository.findById(idAluno);

        if (optionalAluno.isPresent()){
            Aluno aluno = optionalAluno.get();
            alunoRepository.delete(aluno);
            return ResponseEntity.ok("Aluno deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }
    }

}
