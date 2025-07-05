//Classe Concreta Strategy
public class CriterioPreco implements CriterioOrdenacao{
	private boolean crescente;
	public CriterioPreco(boolean crescente){
		this.crescente = crescente;
	}
	public int comparar(Produto a, Produto b){
		int x;
		if(a.getPreco() > b.getPreco()) x = 1;
		else if(a.getPreco() < b.getPreco()) x = -1;
		else x = 0;
		if(crescente == true) return x;
		else return -x;
	}
}