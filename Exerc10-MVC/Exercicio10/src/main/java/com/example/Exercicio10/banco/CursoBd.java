package com.example.Exercicio10.banco;

import com.example.Exercicio10.model.Aluno;
import com.example.Exercicio10.model.Curso;
import com.example.Exercicio10.model.Professor;

import java.util.ArrayList;
import java.util.List;

public class  CursoBd {
    private List<Curso> cursos;

    private List<Aluno> alunos;

    public CursoBd(){
        this.cursos = new ArrayList<>();
    }

    //buscar todos os cursos
    public List<Curso> findAll(){
        return new ArrayList<>(cursos);
    }

    //busca um curso pelo nome do professor
    public Curso getByNomeProfessor(String nomeProfessor){
        return cursos.stream()
                .filter(curso -> curso.getProfessor().getNomeProfessor().equals(nomeProfessor))
                .findFirst()
                .orElse(null);

    }
    //busca um curso pelo numero de sala
    public Curso getByNumeroSala(Integer numeroSala){
        return cursos.stream()
                .filter(curso -> curso.getNumeroSala()== numeroSala)
                .findFirst()
                .orElse(null);

    }

    //busca um curso pelo id
    public Curso getByIdCurso(Long idCurso){
        return cursos.stream()
                .filter(f -> f.getIdCurso() == idCurso)
                .findFirst()
                .orElse(null);
    }

    //insere um curso
    public boolean insert(Curso curso){
        cursos.add(curso);

        return true;
    }

    // insere um novo aluno em um curso
    public Curso insertAluno(Curso curso, Aluno aluno){
        curso.getAlunos().add(aluno);
        return curso;
    }



    //atualiza um curso pelo id
    public boolean update(Long idCurso, Curso curso){
        Curso cursoBd = cursos.stream()
                .filter(b -> b.getIdCurso() == idCurso)
                .findFirst()
                .orElse(null);
        if (cursoBd == null){
            return false;
        }
        cursoBd.setNome(curso.getNome());

        return true;
    }

    //atualiza um aluno pelo id do curso e do aluno
    public boolean updateAluno(int idCurso, int idAluno, Aluno aluno){
        Curso cursoBd = cursos.stream()
                .filter(curso -> curso.getIdCurso() == idCurso)
                .findFirst()
                .orElse(null);

        if (cursoBd == null){
            return false;
        }

        Aluno alunoBd = cursoBd.getAlunos().stream()
                .filter(a -> a.getIdAluno() == idAluno)
                .findFirst()
                .orElse(null);

        if(cursoBd == null){
            return false;
        }

        alunoBd.setNome(aluno.getNome());
        alunoBd.setCpf(aluno.getCpf());

        return true;

    }

    //deleta um curso pelo id

    public boolean delete(Long idCurso){
        Curso cursoBd = cursos.stream()
                .filter(d -> d.getIdCurso() == idCurso)
                .findFirst()
                .orElse(null);
        if (cursoBd == null){
            return false;
        }

        cursos.remove(cursoBd);
        return  true;
    }


}
