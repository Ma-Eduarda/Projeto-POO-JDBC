package datas.testejava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionPostgreSQL {

    private String url;
    private String user;
    private String password;

    public ConnectionPostgreSQL() {
        this.url = "jdbc:postgresql://localhost:5432/loja01";
        this.user = "postgres";
        this.password = "postgres";
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void close(ResultSet rs, PreparedStatement stmt, Connection conexao) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(stmt, conexao);
    }

    public void close(PreparedStatement stmt, Connection conexao) {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conexao != null) conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


/*
CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    preco DECIMAL(10, 2),
    quantidade_estoque INT,
    descricao TEXT
);
*/
