package datas.testejava;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TelaEstoque extends JFrame {

    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final DefaultTableModel modelo = new DefaultTableModel();
    private final JTable tabelaProdutos = new JTable(modelo);

    public TelaEstoque() {
        setTitle("Gerenciamento de Estoque");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Preço");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Descrição");
        carregarProdutos();

        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar Produto");
        JButton btnEditar = new JButton("Editar Produto");
        JButton btnExcluir = new JButton("Excluir Produto");

        btnAdicionar.addActionListener(this::abrirTelaCadastro);
        btnEditar.addActionListener(this::abrirTelaEdicao);
        btnExcluir.addActionListener(this::excluirProduto);

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    public void carregarProdutos() {
        modelo.setRowCount(0);
        List<Produto> produtos = produtoDAO.listarProdutos();
        for (Produto p : produtos) {
            modelo.addRow(new Object[]{p.getId(), p.getNome(), String.format(java.util.Locale.US, "%.2f", p.getPreco()), p.getQtdEstoque(), p.getDescricao()
            });
        }
    }

    private void abrirTelaCadastro(ActionEvent e) {
        new TelaCadastroProduto(this).setVisible(true);
    }

    private void abrirTelaEdicao(ActionEvent e) {
        int linha = tabelaProdutos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para editar.");
            return;
        }
        int id = (int) modelo.getValueAt(linha, 0);
        String nome = (String) modelo.getValueAt(linha, 1);
        double preco = Double.parseDouble(modelo.getValueAt(linha, 2).toString().replace(",", "."));
        int qtd = (int) modelo.getValueAt(linha, 3);
        String descricao = (String) modelo.getValueAt(linha, 4);

        new TelaEditarProduto(this, id, nome, preco, qtd, descricao).setVisible(true);
    }

    private void excluirProduto(ActionEvent e) {
        int linha = tabelaProdutos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.");
            return;
        }
        int id = (int) modelo.getValueAt(linha, 0);
        produtoDAO.excluirProduto(id);
        carregarProdutos();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaEstoque().setVisible(true));
    }
}
