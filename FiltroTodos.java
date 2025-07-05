//Classe Concreta Strategy
public class FiltroTodos implements CriterioFiltro {
    public boolean deveIncluir(Produto produto) {
        return true;
    }
}