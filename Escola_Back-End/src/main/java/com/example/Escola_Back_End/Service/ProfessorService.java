package com.example.Escola_Back_End.Service;

import com.example.Escola_Back_End.DTO.ProfessorDto;
import com.example.Escola_Back_End.Entity.Professor;
import com.example.Escola_Back_End.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> getAllProfessor(){
        return professorRepository.findAll();
    }


    //buscar os professores pelo nome
    public List<Professor> getAllByNomeProfessor(String nomeProfessor){
        return professorRepository.findAllByNomeProfessor(nomeProfessor);
    }

    public Optional<ProfessorDto> getById(Long id){
        Optional<Professor> professorOptional = professorRepository.findById(id);
        if (professorOptional.isPresent()){
            ProfessorDto professorDTO = new ProfessorDto();
            return Optional.of(professorDTO.fromProfessor(professorOptional.get()));
        } else{
            return Optional.empty();
        }
    }

    public ProfessorDto createProfessor(ProfessorDto professorDTO){
        Professor professor = professorDTO.toProfessor();
        professor = professorRepository.save(professor);
        return professorDTO.fromProfessor(professor);
    }

    //atualizar profressor
    public Optional<ProfessorDto> updateProfessor(Long idProfessor, ProfessorDto professorDto){
        Optional<Professor> professorOptional = professorRepository.findById(idProfessor);
        if (professorOptional.isPresent()){
            Professor professor = professorOptional.get();
            professor.setNomeProfessor(professorDto.getNomeProfessor());
            professor.setSobrenome(professorDto.getSobrenome());

            professor = professorRepository.save(professor);

            return Optional.of(professorDto.fromProfessor(professor));
        } else {
            return Optional.empty();
        }
    }

    public boolean delete(Long id){
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return true;
        } else{
            return false;
        }
    }
}
//davi-30/04