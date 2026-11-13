package br.edu.faex.academico.model;

public class Disciplina {
    private Long id;
    private String nome;
    private int cargaHoraria;
    private Curso curso;

    public Disciplina() {
    }

    public Disciplina(String nome, int cargaHoraria, Curso curso) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
