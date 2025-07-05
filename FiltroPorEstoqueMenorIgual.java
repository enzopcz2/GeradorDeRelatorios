//Classe Concreta Strategy
public class FiltroPorEstoqueMenorIgual implements CriterioFiltro {
    private int limite;
    public FiltroPorEstoqueMenorIgual(int limite) {
        this.limite = limite;
    }
    public boolean deveIncluir(Produto produto) {
        return produto.getQtdEstoque() <= limite;
    }
}