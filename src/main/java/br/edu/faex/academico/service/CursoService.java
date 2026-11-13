package br.edu.faex.academico.service;

import br.edu.faex.academico.model.Curso;
import br.edu.faex.academico.repository.CursoRepository;

import java.util.List;

public class CursoService {
    private CursoRepository repository;
    private Long proximoId = 1L;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Curso curso) {
        if (curso.getNome() == null || curso.getNome().isBlank()) {
            System.out.println("O nome do curso é obrigatório.");
            return;
        }

        if (curso.getModalidade() == null || curso.getModalidade().isBlank()) {
            System.out.println("A modalidade do curso é obrigatória.");
            return;
        }

        curso.setId(proximoId);
        proximoId++;

        this.repository.salvar(curso);
    }

    public List<Curso> listar() {
        return repository.listar();
    }

    public Curso buscarPorId(Long id) {
        Curso curso = repository.buscarPorId(id);
        if (curso == null) {
            return null;
        }
        return curso;
    }

    public void excluir(Long id) {
        Curso curso = repository.buscarPorId(id);
        if (curso == null) {
            System.out.println("Curso não encontrado.");
            return;
        }
        repository.excluir(id);
        System.out.println("Curso excluído com sucesso.");
    }

    public void atualizar(Curso cursoEditado) {
        Curso curso = repository.buscarPorId(cursoEditado.getId());
        if (curso == null) {
            System.out.println("Curso não encontrado.");
            return;
        }
        if (cursoEditado.getNome() == null || cursoEditado.getNome().isBlank()) {
            System.out.println("O nome do curso é obrigatório.");
            return;
        }
        if (cursoEditado.getModalidade() == null || cursoEditado.getModalidade().isBlank()) {
            System.out.println("A modalidade do curso é obrigatória.");
            return;
        }
        repository.atualizar(cursoEditado);
        System.out.println("Curso atualizado com sucesso.");
    }
}
