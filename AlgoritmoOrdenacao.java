import java.util.List;

//Interface Strategy
public interface AlgoritmoOrdenacao {
    void ordenar(List<Produto> produtos, CriterioOrdenacao criterio);
}
