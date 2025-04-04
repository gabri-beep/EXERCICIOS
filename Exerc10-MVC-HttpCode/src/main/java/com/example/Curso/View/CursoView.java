package com.example.Curso.View;

import com.example.Curso.Controller.CursoController;
import com.example.Curso.model.Aluno;
import com.example.Curso.model.Curso;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoView {

    CursoController cursoController = new CursoController();

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso encontrado"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public ResponseEntity<List<Curso>> getCurso(@RequestParam(required = false) String nomeProfessor,
                                                @RequestParam(required = false) Integer sala) {
                return cursoController.getAll();

    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso encontrado"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public ResponseEntity<Curso> getById(@PathVariable int id){
        return cursoController.getById(id);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso cadastrado"),
            @ApiResponse(responseCode = "400", description = "Erro ao inserir curso")
    })
    public ResponseEntity<Curso> insert(@RequestBody Curso curso){
        return cursoController.insertBanco(curso);
    }

    /*@PostMapping("/{idCurso}/aluno")
    public Curso insertAluno(@RequestBody Aluno aluno, @PathVariable int idCurso){
        return cursoController.insertAluno(idCurso, aluno);
    }*/

    // esta função fara o mesmo insert que a função a cima, porém com melhores práticas de programação
    @PostMapping("/{idCurso}/alunoMelhorado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno inserido com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro ao inserir aluno")
    })
    public ResponseEntity<Curso> insertAlunoMelhorado(@RequestBody Aluno aluno, @PathVariable int idCurso){
        return cursoController.insertAlunoMelhorado(idCurso, aluno);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public ResponseEntity<Curso> update(@RequestBody Curso curso, @PathVariable int id){
        return cursoController.update(id, curso);
    }

    @PutMapping("/{idCurso}/aluno/{idAluno}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    public ResponseEntity<String> update(@PathVariable int idCurso, @PathVariable int idAluno, @RequestBody Aluno aluno){
        return cursoController.updateAluno(idCurso, idAluno, aluno);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public ResponseEntity<String> delete(@PathVariable int id){
        return cursoController.delete(id);
    }
}
