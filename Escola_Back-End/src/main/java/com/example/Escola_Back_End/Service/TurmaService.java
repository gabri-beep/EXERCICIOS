package com.example.Escola_Back_End.Service;

import com.example.Escola_Back_End.DTO.TurmaDTO;
import com.example.Escola_Back_End.Entity.Aluno;
import com.example.Escola_Back_End.Entity.Turma;
import com.example.Escola_Back_End.Repository.AlunoRepository;
import com.example.Escola_Back_End.Repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Turma> getAllTurmas() {
        return turmaRepository.findAll();
    }

    public List<Turma> getAllTurmasByNome(String nome) {
        return turmaRepository.getAllByNomeTurma(nome);
    }

    public Optional<TurmaDTO> getById(Long id) {
        Optional<Turma> optional = turmaRepository.findById(id);

        if (optional.isPresent()) {
            TurmaDTO turmaDTO = new TurmaDTO();

            return Optional.of(turmaDTO.fromTurma(optional.get()));
        } else {
            return Optional.empty();
        }
    }

    public TurmaDTO createTurma(TurmaDTO turmaDTO) {
        Turma turma = turmaDTO.toTurma();
        turma = turmaRepository.save(turma);

        return turmaDTO.fromTurma(turma);
    }

    public Optional<TurmaDTO> updateTurma(Long id, TurmaDTO dto) {
        Optional<Turma> optional = turmaRepository.findById(id);

        if (optional.isPresent()) {
            Turma turma = optional.get();

            turma.setSigla(dto.getSigla());
            turma.setNumeroSala(dto.getNumeroSala());
            turma.setNomeTurma(dto.getNomeTurma());
            turma.setProfessor(dto.getProfessor());

            turma = turmaRepository.save(turma);

            return Optional.of(dto.fromTurma(turma));
        } else {
            return Optional.empty();
        }
    }

    // Adicionar 'Aluno' na 'Turma'
    public boolean addAlunoTurma(Long id, Long idAluno){
        Optional<Turma> optionalTurma = turmaRepository.findById(id);
        if(!optionalTurma.isPresent()){
            return false;
        }

        Optional<Aluno> optionalAluno = alunoRepository.findById(idAluno);
        if(!optionalAluno.isPresent()){
            return false;
        }

        Turma turma = optionalTurma.get();
        Aluno aluno = optionalAluno.get();

        aluno.setTurma(turma);
        alunoRepository.save(aluno);

        return true;
    }

    // Remove 'Aluno' da 'Turma'
    public boolean removeAlunoTurma(Long id, Long idAluno){
        // busca o aluno e verifica se ele existe
        Optional<Aluno> optionalAluno = alunoRepository.findById(idAluno);
        if(!optionalAluno.isPresent()){
            return false;
        }
        // instancia as entidades Turma e Aluno
        Aluno aluno = optionalAluno.get();

        // verifica se o aluno tem uma turma
        // verifica se a turma que esta no aluno é realmente a turma que deseja remover
        if (aluno.getTurma() != null && aluno.getTurma().getIdTurma().equals(id)) {
            aluno.setTurma(null); // remove o aluno da turma
            alunoRepository.save(aluno); // salva no banco de dados
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (turmaRepository.existsById(id)) {
            turmaRepository.deleteById(id);

            return true;
        } else {
            return false;
        }
    }
}
