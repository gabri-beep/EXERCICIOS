package com.example.Escola_Back_End.Controller;

import com.example.Escola_Back_End.Entity.Professor;
import com.example.Escola_Back_End.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Escola_Back_End.DTO.ProfessorDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;


    @GetMapping("/{idProfessor}")
    public ResponseEntity<ProfessorDto> getById(@PathVariable Long idProfessor) {
        Optional<ProfessorDto> professorDTOOptional = professorService.getById(idProfessor);
        if (professorDTOOptional.isPresent()) {
            return ResponseEntity.ok(professorDTOOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Professor> getAll(@RequestParam(required = false) String nomeProfessor) {
        if (nomeProfessor != null && !nomeProfessor.isEmpty()) {
            return professorService.getAllByNomeProfessor(nomeProfessor);
        }
        return professorService.getAllProfessor();
    }

    @PostMapping
    public ResponseEntity<ProfessorDto> create(@RequestBody ProfessorDto professorDTO) {
        ProfessorDto professorDTOSave = professorService.createProfessor(professorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorDTOSave);
    }

    @DeleteMapping("/{idProfessor}")
    public ResponseEntity<Void> delete(@PathVariable Long idProfessor) {
        if (professorService.delete(idProfessor)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idProfessor}")
    public ResponseEntity<ProfessorDto> updateProfessor(@PathVariable Long idProfessor, @RequestBody ProfessorDto
            professorDto) {
        Optional<ProfessorDto> professorDtoOptional = professorService.updateProfessor(idProfessor, professorDto);
        if (professorDtoOptional.isPresent()) {
            return ResponseEntity.ok(professorDtoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
//davi-30/04