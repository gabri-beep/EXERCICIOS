package com.example.Escola_Back_End.DTO;

import com.example.Escola_Back_End.Entity.Aluno;
import com.example.Escola_Back_End.Entity.Turma;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO implements Serializable {

    private Long idAluno;
    private String nomeAluno;
    private String cpf;

    @JsonIgnore
    private Turma turma;

    public Aluno toAluno(){
        return new Aluno(
           this.idAluno,
           this.nomeAluno,
           this.cpf,
           this.turma
        );
    }

    public AlunoDTO fromAluno(Aluno aluno){
        return new AlunoDTO(
           aluno.getIdAluno(),
           aluno.getNomeAluno(),
           aluno.getCpf(),
           aluno.getTurma()
        );
    }

}
