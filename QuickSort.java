import java.util.List;

//Classe Concreta Strategy
public class QuickSort implements AlgoritmoOrdenacao{
	public void ordenar(List<Produto> produtos, CriterioOrdenacao criterio){
        ordena(produtos, 0, produtos.size() - 1, criterio);
	}
	public void ordena(List<Produto> produtos, int ini, int fim, CriterioOrdenacao criterio){
		if(ini < fim) {
			int q = particiona(produtos, ini, fim, criterio);
			ordena(produtos, ini, q, criterio);
			ordena(produtos, q + 1, fim, criterio);
		}
	}
	private int particiona(List<Produto> produtos, int ini, int fim, CriterioOrdenacao criterio){
		Produto x = produtos.get(ini);
		int i = (ini - 1);
		int j = (fim + 1);
		while(true){
			do{ 
				j--;
			} while(criterio.comparar(produtos.get(j), x) > 0);
			do{
				i++;
			} while(criterio.comparar(produtos.get(i), x) < 0);
			if(i < j){
				Produto temp = produtos.get(i);
				produtos.set(i, produtos.get(j)); 				
				produtos.set(j, temp);
			}
			else return j;
		}
	}
}