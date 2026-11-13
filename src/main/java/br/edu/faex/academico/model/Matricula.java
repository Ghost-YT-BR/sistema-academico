package br.edu.faex.academico.model;

public class Matricula {
    private Long id;
    private Aluno aluno;
    private Disciplina disciplina;
    private double nota1;
    private double nota2;

    public Matricula() {
    }

    public Matricula(Aluno aluno, Disciplina disciplina) {
        this.aluno = aluno;
        this.disciplina = disciplina;
    }

    public Long getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public double getNota1() {
        return nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getMedia() {
        return (nota1 + nota2) / 2.0;
    }

    public boolean isAprovado() {
        return getMedia() >= 6.0;
    }
}
