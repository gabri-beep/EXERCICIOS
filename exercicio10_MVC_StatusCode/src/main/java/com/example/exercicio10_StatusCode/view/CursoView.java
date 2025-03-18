package com.example.exercicio10_StatusCode.view;

import com.example.exercicio10_StatusCode.controller.CursoController;
import com.example.exercicio10_StatusCode.model.Curso;
import com.example.exercicio10_StatusCode.model.Aluno;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ApiResponses(value = {

    }
    )
    public ResponseEntity<Curso> getByIdCurso(@PathVariable Long idCurso) {
        return cursoController.getByIdCurso(idCurso);
    }



    @PostMapping
    @ApiResponses(value = {
            //@ApiResponses(responseCode = "201", description = "Curso cadastrado"),
            @ApiResponse(responseCode = "400", description = "Erro ao inserir curso")
    })
    public ResponseEntity<Curso> insert(@RequestBody Curso curso){

        return cursoController.insertBanco(curso);

    }


    @PostMapping("/{idCurso}/aluno")
    public Curso insertAluno(@RequestBody Aluno aluno, @PathVariable Long idCurso){
        return cursoController.insertAluno(idCurso, aluno);
    }

    @PutMapping("/{idCurso}")
    public Curso update(@RequestBody Curso curso, @PathVariable Long idCurso ) {
        return cursoController.update(idCurso, curso );
    }

    @PutMapping("/{idCurso}/aluno/{idAluno}")
    public boolean update(@PathVariable int idCurso, @PathVariable int idAluno, @RequestBody Aluno aluno){
        return cursoController.updateAluno(idCurso, idAluno, aluno);
    }

    @DeleteMapping("/{idCurso}")
    public boolean delete( @PathVariable Long idCurso){
        return  cursoController.delete(idCurso);
    }
}
