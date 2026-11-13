package br.edu.faex.academico.repository;

import br.edu.faex.academico.config.Conexao;
import br.edu.faex.academico.model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {

    private List<Aluno> alunos = new ArrayList<>();

    public void salvar(Aluno aluno) {
        String sql = "insert into alunos (nome, email, ativo) VALUES (?, ?, ?)";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, aluno.getNome());
            comando.setString(2, aluno.getEmail());
            comando.setBoolean(3, aluno.isAtivo());

            comando.executeUpdate();

            comando.close();
            conexao.close();

            System.out.println("Aluno cadastrado com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public List<Aluno> listar() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM alunos";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Aluno aluno = new Aluno();

                aluno.setId(resultado.getLong("id"));
                aluno.setNome(resultado.getString("nome"));
                aluno.setEmail(resultado.getString("email"));
                aluno.setAtivo(resultado.getBoolean("ativo"));

                alunos.add(aluno);
            }

            resultado.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }

        return alunos;
    }

    public Aluno buscarPorId(Long id) {
        String sql = "SELECT * FROM alunos WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setLong(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                Aluno aluno = new Aluno();

                aluno.setId(resultado.getLong("id"));
                aluno.setNome(resultado.getString("nome"));
                aluno.setEmail(resultado.getString("email"));
                aluno.setAtivo(resultado.getBoolean("ativo"));

                resultado.close();
                comando.close();
                conexao.close();

                return aluno;
            }

            resultado.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
        }

        return null;
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM alunos WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setLong(1, id);

            int linhasAfetadas = comando.executeUpdate();

            comando.close();
            conexao.close();

            if (linhasAfetadas > 0) {
                System.out.println("Aluno excluído com sucesso!");
            } else {
                System.out.println("Aluno não encontrado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir aluno: " + e.getMessage());
        }
    }

    public void atualizar(Aluno alunoEditado) {
        for (Aluno aluno : alunos) {
            if (aluno.getId().equals(alunoEditado.getId())) {
                aluno.setNome(alunoEditado.getNome());
                aluno.setEmail(alunoEditado.getEmail());
                return;
            }
        }
    }
}
