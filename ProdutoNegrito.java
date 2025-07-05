//Decorador de formatação
public class ProdutoNegrito extends ProdutoDecorado {
    public ProdutoNegrito(Produto p) { super(p); }
    public String formataParaImpressao() {
        return "<span style=\"font-weight:bold\">" + produtoOriginal.formataParaImpressao() + "</span>";
    }
}