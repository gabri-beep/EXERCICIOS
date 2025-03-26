package com.example.Jpa_02.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.lang.reflect.Method;
import java.util.List;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;
    private String nome;
    private int numeroSala;

    @OneToOne
    @JoinColumn(name = "idProfessor", referencedColumnName = "idProfessor")
    private Professor professor;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Aluno> alunos;

    public Curso() {
    }

    public Curso(Long idCurso, String nome, int numeroSala) {
        this.idCurso = idCurso;
        this.nome = nome;
        this.numeroSala = numeroSala;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public Professor getProfessor(){
        return professor;
    }

    public void setProfessor(Professor professor){
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}
