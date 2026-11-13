package br.edu.faex.academico.repository;

import br.edu.faex.academico.config.Conexao;
import br.edu.faex.academico.model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoRepository {

    private List<Curso> cursos = new ArrayList<>();

    public void salvar(Curso curso) {
        String sql = "insert into cursos (nome, modalidade) VALUES (?, ?)";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, curso.getNome());
            comando.setString(2, curso.getModalidade());

            comando.executeUpdate();

            comando.close();
            conexao.close();

            System.out.println("Curso cadastrado com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public List<Curso> listar() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM cursos";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Curso curso = new Curso();

                curso.setId(resultado.getLong("id"));
                curso.setNome(resultado.getString("nome"));
                curso.setModalidade(resultado.getString("modalidade"));

                cursos.add(curso);
            }

            resultado.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }

        return cursos;
    }

    public Curso buscarPorId(Long id) {
        String sql = "SELECT * FROM cursos WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setLong(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                Curso curso = new Curso();

                curso.setId(resultado.getLong("id"));
                curso.setNome(resultado.getString("nome"));
                curso.setModalidade(resultado.getString("modalidade"));

                resultado.close();
                comando.close();
                conexao.close();

                return curso;
            }

            resultado.close();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar curso: " + e.getMessage());
        }

        return null;
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM cursos WHERE id = ?";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setLong(1, id);

            int linhasAfetadas = comando.executeUpdate();

            comando.close();
            conexao.close();

            if (linhasAfetadas > 0) {
                System.out.println("Curso excluído com sucesso!");
            } else {
                System.out.println("Curso não encontrado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir curso: " + e.getMessage());
        }
    }

    public void atualizar(Curso cursoEditado) {
        for (Curso curso : cursos) {
            if (curso.getId().equals(cursoEditado.getId())) {
                curso.setNome(cursoEditado.getNome());
                curso.setModalidade(cursoEditado.getModalidade());
                return;
            }
        }
    }
}
