package com.example.Exercicio10.view;

import com.example.Exercicio10.controller.CursoController;
import com.example.Exercicio10.model.Aluno;
import com.example.Exercicio10.model.Curso;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curso")
public class CursoView {
    CursoController cursoController = new CursoController();

    @GetMapping()
    public Object getAll(@RequestParam(required = false)String nomeProfessor,
                         @RequestParam(required = false)Integer numeroSala) {
        if (nomeProfessor != null){
            return cursoController.getByNomeProfessor(nomeProfessor);
        } else if (numeroSala != null) {
            return cursoController.getByNumeroSala(numeroSala);
        }else {
            return cursoController.getAll();
        }
    }

    @GetMapping("/{idCurso}")
    public Curso getByIdCurso(@PathVariable Long idCurso) {
        return cursoController.getByIdCurso(idCurso);
    }



    @PostMapping
    public boolean insert(@RequestBody Curso curso){

        return cursoController.insertBanco(curso);

    }

    @PostMapping("/aluno")
    public Aluno insertAluno(@RequestBody Aluno aluno, Curso curso){

        return cursoController.insertAluno(aluno);
    }

    @PutMapping("/{idCurso}")
    public Curso update(@RequestBody Curso curso, @PathVariable Long idCurso ) {
        return cursoController.update(idCurso, curso );
    }

    @DeleteMapping("/{idCurso}")
    public boolean delete( @PathVariable Long idCurso){
        return  cursoController.delete(idCurso);
    }

}
