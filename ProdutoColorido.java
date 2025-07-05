//Decorador de formatação
public class ProdutoColorido extends ProdutoDecorado {
    private String cor;
    public ProdutoColorido(Produto p, String cor) {
        super(p);
        this.cor = cor;
    }
    public String formataParaImpressao() {
        return "<span style=\"color:" + cor + "\">" + produtoOriginal.formataParaImpressao() + "</span>";
    }
}