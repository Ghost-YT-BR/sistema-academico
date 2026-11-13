package br.edu.faex.academico.config;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private final static String URL =
            "jdbc:mysql://localhost:3306/sistema_academico";
    private final static String USUARIO = "root";
    private final static String SENHA = "";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(
                URL,
                USUARIO,
                SENHA
        );
    }






}
