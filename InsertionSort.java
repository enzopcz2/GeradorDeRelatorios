import java.util.List;

//Classe Concreta Strategy
class InsertionSort implements AlgoritmoOrdenacao{
	public void ordenar(List<Produto> produtos, CriterioOrdenacao criterio){
		int n = produtos.size();
        for (int i = 1; i < n; i++) {
            Produto chave = produtos.get(i);
            int j = i - 1;
            while (j >= 0 && criterio.comparar(produtos.get(j), chave) > 0) {
                produtos.set(j + 1, produtos.get(j));
                j = j - 1;
            }
            produtos.set(j + 1, chave);
        }
    }
}