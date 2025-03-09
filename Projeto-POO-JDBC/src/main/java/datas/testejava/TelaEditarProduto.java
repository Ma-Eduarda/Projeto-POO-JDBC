package datas.testejava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaEditarProduto extends JFrame {
    private final JTextField txtNome = new JTextField(20);
    private final JTextField txtPreco = new JTextField(10);
    private final JTextField txtQuantidade = new JTextField(5);
    private final JTextArea txtDescricao = new JTextArea(3, 20);
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final int produtoId;
    private final TelaEstoque telaEstoque;

    public TelaEditarProduto(TelaEstoque telaEstoque, int id, String nome, double preco, int quantidade, String descricao) {
        this.produtoId = id;
        this.telaEstoque = telaEstoque;

        setTitle("Editar Produto");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        txtNome.setText(nome);
        txtPreco.setText(String.valueOf(preco));
        txtQuantidade.setText(String.valueOf(quantidade));
        txtDescricao.setText(descricao);

        addLabelAndField("Nome:", txtNome, gbc, 0);
        addLabelAndField("Preço:", txtPreco, gbc, 1);
        addLabelAndField("Quantidade:", txtQuantidade, gbc, 2);
        addLabelAndField("Descrição:", new JScrollPane(txtDescricao), gbc, 3);

        JPanel panelButtons = new JPanel();
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(this::salvarEdicao);
        btnCancelar.addActionListener(e -> dispose());

        panelButtons.add(btnSalvar);
        panelButtons.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelButtons, gbc);
    }

    private void addLabelAndField(String label, Component field, GridBagConstraints gbc, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel(label), gbc);

        gbc.gridx = 1;
        add(field, gbc);
    }

    private void salvarEdicao(ActionEvent e) {
        try {
            String nome = txtNome.getText().trim();
            if (nome.isEmpty()) throw new IllegalArgumentException("O nome não pode estar vazio!");

            double preco = Double.parseDouble(txtPreco.getText().trim());
            int quantidade = Integer.parseInt(txtQuantidade.getText().trim());
            String descricao = txtDescricao.getText().trim();

            produtoDAO.atualizarProduto(new Produto(produtoId, nome, preco, quantidade, descricao));
            JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso!");

            telaEstoque.carregarProdutos();
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro: Preço e Quantidade devem ser números válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
