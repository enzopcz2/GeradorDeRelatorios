//Classe Concreta Strategy
public class FiltroPorDescricaoContendo implements CriterioFiltro {
    private String substring;
    public FiltroPorDescricaoContendo(String substring) {
        this.substring = substring.toLowerCase();
    }
    public boolean deveIncluir(Produto produto) {
        return produto.getDescricao().toLowerCase().contains(substring);
    }
}