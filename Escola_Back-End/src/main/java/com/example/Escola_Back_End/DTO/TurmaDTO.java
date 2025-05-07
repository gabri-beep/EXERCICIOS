package com.example.Escola_Back_End.DTO;

import com.example.Escola_Back_End.Entity.Aluno;
import com.example.Escola_Back_End.Entity.Professor;
import com.example.Escola_Back_End.Entity.Turma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaDTO implements Serializable {
    // Atributos
    private Long idTurma;
    private String sigla;
    private int numeroSala;
    private String nomeTurma;

    private Professor professor;
    private List<Aluno> alunos;

    public Turma toTurma() {
        return new Turma(
                this.idTurma,
                this.sigla,
                this.numeroSala,
                this.nomeTurma,
                this.professor,
                this.alunos
        );
    }

    public TurmaDTO fromTurma(Turma turma) {
        return new TurmaDTO(
                turma.getIdTurma(),
                turma.getSigla(),
                turma.getNumeroSala(),
                turma.getNomeTurma(),
                turma.getProfessor(),
                turma.getAlunos()
        );
    }
}
