//Classe abstrata Decorator
public abstract class ProdutoDecorado implements Produto {
    protected Produto produtoOriginal;
    public ProdutoDecorado(Produto produtoOriginal) {
        this.produtoOriginal = produtoOriginal;
    }
    public void setQtdEstoque(int qtdEstoque) { produtoOriginal.setQtdEstoque(qtdEstoque); }
    public void setPreco(double preco) { produtoOriginal.setPreco(preco); }
    public int getId() { return produtoOriginal.getId(); }
    public String getDescricao() { return produtoOriginal.getDescricao(); }
    public String getCategoria() { return produtoOriginal.getCategoria(); }
    public int getQtdEstoque() { return produtoOriginal.getQtdEstoque(); }
    public double getPreco() { return produtoOriginal.getPreco(); }
}