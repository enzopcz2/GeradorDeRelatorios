//Classe Concreta Strategy
public class FiltroPorIntervaloDePreco implements CriterioFiltro {
    private double minimo;
    private double maximo;
    public FiltroPorIntervaloDePreco(double minimo, double maximo) {
        this.minimo = minimo;
        this.maximo = maximo;
    }
    public boolean deveIncluir(Produto produto) {
        double preco = produto.getPreco();
        return preco >= minimo && preco <= maximo;
    }
}