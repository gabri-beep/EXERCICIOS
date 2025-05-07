package com.example.Escola_Back_End.DTO;

import com.example.Escola_Back_End.Entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDto {
    private Long idProfessor;
    private String nomeProfessor;
    private String sobrenome;

    public Professor toProfessor(){
        return new Professor(
                this.idProfessor,
                this.nomeProfessor,
                this.sobrenome
        );
    }

    public ProfessorDto fromProfessor(Professor professor){
        return new ProfessorDto(
                professor.getIdProfessor(),
                professor.getNomeProfessor(),
                professor.getSobrenome()
        );
    }
}
//davi-30/04