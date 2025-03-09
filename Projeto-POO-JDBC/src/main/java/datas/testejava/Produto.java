package datas.testejava;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int qtdEstoque;
    private String descricao;

    public Produto() {}

    public Produto(int id, String nome, double preco, int qtdEstoque, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        this.descricao = descricao;
    }

    public int  getId() {
        return id;
    }
    
   public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }
    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
