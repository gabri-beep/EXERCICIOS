package com.example.Escola_Back_End.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long idAluno;
    private String nomeAluno;
    @Column(unique = true)
    private String cpf;

    @ManyToOne()
    @JoinColumn(name = "idTurma", referencedColumnName = "idTurma")
    @JsonIgnore
    private Turma turma;

    public Aluno(Long idAluno, String nomeAluno, String cpf) {
        this.idAluno = idAluno;
        this.nomeAluno = nomeAluno;
        this.cpf = cpf;
    }
}
