//Decorador de formatação
public class ProdutoItalico extends ProdutoDecorado {
    public ProdutoItalico(Produto p) { super(p); }
    public String formataParaImpressao() {
        return "<span style=\"font-style:italic\">" + produtoOriginal.formataParaImpressao() + "</span>";
    }
}