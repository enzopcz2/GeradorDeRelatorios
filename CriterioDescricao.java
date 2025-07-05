//Classe Concreta Strategy
public class CriterioDescricao implements CriterioOrdenacao{
	private boolean crescente;
	public CriterioDescricao(boolean crescente){
		this.crescente = crescente;
	}
	public int comparar(Produto a, Produto b){
		int x = a.getDescricao().compareToIgnoreCase(b.getDescricao());
		if(crescente == true) return x;
		else return -x;
	}
}