package br.edu.faex.academico.repository;

import br.edu.faex.academico.config.Conexao;
import br.edu.faex.academico.model.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepository {

    private List<Professor> professores = new ArrayList<>();

    public void salvar(Professor professor) {
        String sql = "insert into professores (nome, email) VALUES (?, ?)";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, professor.getNome());
            comando.setString(2, professor.getEmail());

            comando.executeUpdate();

            comando.close();
            conexao.close();

            System.out.println("Professor cadastrado com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public List<Professor> listar() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professores";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Professor professor = new Professor();

                professor.setId(resultado.getLong("id"));
                professor.setNome(resultado.getString("nome"));
                professor.setEmail(resultado.getString("email"));

                professores.add(professor);
            }

            resultado.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }

        return professores;
    }

    public Professor buscarPorId(Long id) {
        String sql = "SELECT * FROM professores WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setLong(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                Professor professor = new Professor();

                professor.setId(resultado.getLong("id"));
                professor.setNome(resultado.getString("nome"));
                professor.setEmail(resultado.getString("email"));

                resultado.close();
                comando.close();
                conexao.close();

                return professor;
            }

            resultado.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar professor: " + e.getMessage());
        }

        return null;
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM professores WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setLong(1, id);

            int linhasAfetadas = comando.executeUpdate();

            comando.close();
            conexao.close();

            if (linhasAfetadas > 0) {
                System.out.println("Professor excluído com sucesso!");
            } else {
                System.out.println("Professor não encontrado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir professor: " + e.getMessage());
        }
    }

    public void atualizar(Professor professorEditado) {
        for (Professor professor : professores) {
            if (professor.getId().equals(professorEditado.getId())) {
                professor.setNome(professorEditado.getNome());
                professor.setEmail(professorEditado.getEmail());
                return;
            }
        }
    }
}
