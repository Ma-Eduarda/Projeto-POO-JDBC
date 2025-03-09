package datas.testejava;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private final ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try (Connection conexao = postgres.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id")); 
                produto.setNome(rs.getString("nome")); 
                produto.setPreco(rs.getDouble("preco")); 
                produto.setQtdEstoque(rs.getInt("quantidade_estoque")); 
                produto.setDescricao(rs.getString("descricao")); 
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO produto (nome, preco, quantidade_estoque, descricao) VALUES (?, ?, ?, ?)";
        try (Connection conexao = postgres.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome()); 
            stmt.setDouble(2, produto.getPreco()); 
            stmt.setInt(3, produto.getQtdEstoque()); 
            stmt.setString(4, produto.getDescricao()); 
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, preco = ?, quantidade_estoque = ?, descricao = ? WHERE id = ?";
        try (Connection conexao = postgres.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome()); 
            stmt.setDouble(2, produto.getPreco()); 
            stmt.setInt(3, produto.getQtdEstoque()); 
            stmt.setString(4, produto.getDescricao()); 
            stmt.setInt(5, produto.getId()); 
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirProduto(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        try (Connection conexao = postgres.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
