package br.edu.faex.academico.service;

import br.edu.faex.academico.model.Professor;
import br.edu.faex.academico.repository.ProfessorRepository;

import java.util.List;

public class ProfessorService {
    private ProfessorRepository repository;
    private Long proximoId = 1L;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Professor professor) {
        if (professor.getNome() == null || professor.getNome().isBlank()) {
            System.out.println("O nome do professor é obrigatório.");
            return;
        }

        if (professor.getEmail() == null || professor.getEmail().isBlank()) {
            System.out.println("O e-mail do professor é obrigatório.");
            return;
        }

        if (!professor.getEmail().contains("@")) {
            System.out.println("E-mail inválido.");
            return;
        }

        if (repository.listar().stream()
                .anyMatch(p -> p.getEmail().equalsIgnoreCase(professor.getEmail()))) {
            System.out.println("E-mail já cadastrado.");
            return;
        }

        professor.setId(proximoId);
        proximoId++;

        this.repository.salvar(professor);
    }

    public List<Professor> listar() {
        return repository.listar();
    }

    public Professor buscarPorId(Long id) {
        Professor professor = repository.buscarPorId(id);
        if (professor == null) {
            return null;
        }
        return professor;
    }

    public void excluir(Long id) {
        Professor professor = repository.buscarPorId(id);
        if (professor == null) {
            System.out.println("Professor não encontrado.");
            return;
        }
        repository.excluir(id);
        System.out.println("Professor excluído com sucesso.");
    }

    public void atualizar(Professor professorEditado) {
        Professor professor = repository.buscarPorId(professorEditado.getId());
        if (professor == null) {
            System.out.println("Professor não encontrado.");
            return;
        }
        if (professorEditado.getNome() == null || professorEditado.getNome().isBlank()) {
            System.out.println("O nome do professor é obrigatório.");
            return;
        }
        if (professorEditado.getEmail() == null || professorEditado.getEmail().isBlank()) {
            System.out.println("O e-mail do professor é obrigatório.");
            return;
        }
        if (!professorEditado.getEmail().contains("@")) {
            System.out.println("E-mail inválido.");
            return;
        }
        if (repository.listar().stream()
                .anyMatch(p -> p.getEmail().equalsIgnoreCase(professorEditado.getEmail())
                        && !p.getId().equals(professorEditado.getId()))) {
            System.out.println("E-mail já cadastrado.");
            return;
        }
        repository.atualizar(professorEditado);
        System.out.println("Professor atualizado com sucesso.");
    }
}
