package br.edu.faex.academico.model;

public class Curso {
    private Long id;
    private String nome;
    private String modalidade;

    public Curso() {
    }

    public Curso(String nome, String modalidade) {
        this.nome = nome;
        this.modalidade = modalidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }
}
