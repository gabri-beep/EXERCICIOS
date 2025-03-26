package com.example.Jpa_02.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAluno;
    private String nome;
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "idCurso", referencedColumnName = "idCurso")
    @JsonBackReference
    private Curso curso;

    public Aluno(){

    }

    public Aluno(Long idAluno, String nome, String cpf) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
