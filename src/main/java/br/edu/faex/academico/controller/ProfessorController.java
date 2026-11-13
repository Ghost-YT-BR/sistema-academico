package br.edu.faex.academico.controller;

import br.edu.faex.academico.model.Professor;
import br.edu.faex.academico.service.ProfessorService;

import java.util.List;

public class ProfessorController {
    private ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    public void cadastrar(Professor professor) {
        this.service.cadastrar(professor);
    }

    public List<Professor> listar() {
        return this.service.listar();
    }

    public Professor buscarPorId(long id) {
        return service.buscarPorId(id);
    }

    public void excluir(Long id) {
        service.excluir(id);
    }

    public void atualizar(Professor professorEditado) {
        service.atualizar(professorEditado);
    }
}
