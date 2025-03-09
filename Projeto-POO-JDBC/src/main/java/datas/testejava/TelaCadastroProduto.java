package datas.testejava;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroProduto extends JFrame {
    private final JTextField txtNome = new JTextField(20);
    private final JTextField txtPreco = new JTextField(10);
    private final JTextField txtQuantidade = new JTextField(5);
    private final JTextArea txtDescricao = new JTextArea(3, 20);
    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    public TelaCadastroProduto(TelaEstoque telaEstoque) {
        setTitle("Adicionar Produto");
        setSize(350, 250);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

      
        addLabelAndField("Nome:", txtNome, gbc, 0);
        addLabelAndField("Preço:", txtPreco, gbc, 1);
        addLabelAndField("Quantidade:", txtQuantidade, gbc, 2);
        addLabelAndField("Descrição:", new JScrollPane(txtDescricao), gbc, 3);

        
        JPanel panelButtons = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnSair = new JButton("Sair");

        btnAdicionar.addActionListener(e -> adicionarProduto(telaEstoque));
        btnSair.addActionListener(e -> dispose()); 

        panelButtons.add(btnAdicionar);
        panelButtons.add(btnSair);

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

    public void adicionarProduto(TelaEstoque telaEstoque) {
        try {
            String nome = txtNome.getText().trim();
            if (nome.isEmpty()) throw new IllegalArgumentException("O nome não pode estar vazio!");

            double preco = Double.parseDouble(txtPreco.getText().trim());
            int quantidade = Integer.parseInt(txtQuantidade.getText().trim());
            String descricao = txtDescricao.getText().trim();

            produtoDAO.adicionarProduto(new Produto(0, nome, preco, quantidade, descricao));
            JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");

            telaEstoque.carregarProdutos(); 
            dispose(); 
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro: Preço e Quantidade devem ser números válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
