//Classe Concreta Strategy
public class CriterioEstoque implements CriterioOrdenacao{
	private boolean crescente;
	public CriterioEstoque(boolean crescente){
		this.crescente = crescente;
	}
	public int comparar(Produto a, Produto b){
		int x;
		if(a.getQtdEstoque() > b.getQtdEstoque()) x = 1;
		else if(a.getQtdEstoque() < b.getQtdEstoque()) x = -1;
		else x = 0;
		if(crescente == true) return x;
		else return -x;
	}
}