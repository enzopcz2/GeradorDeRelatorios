//Classe Concreta Strategy
public class FiltroPorCategoria implements CriterioFiltro {
    private String categoria;
    public FiltroPorCategoria(String categoria) {
        this.categoria = categoria.toLowerCase();
    }
    public boolean deveIncluir(Produto produto) {
        return produto.getCategoria().toLowerCase().equals(categoria);
    }
}