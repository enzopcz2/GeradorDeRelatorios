import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeradorDeRelatorios {
    private AlgoritmoOrdenacao algoritmo;
    private CriterioOrdenacao criterioOrdenacao;
    private CriterioFiltro criterioFiltro;

    public GeradorDeRelatorios(AlgoritmoOrdenacao algoritmo, CriterioOrdenacao criterioOrdenacao, CriterioFiltro criterioFiltro) {
        this.algoritmo = algoritmo;
        this.criterioOrdenacao = criterioOrdenacao;
        this.criterioFiltro = criterioFiltro;
    }

    public void geraRelatorio(List<Produto> produtos) throws IOException {
        algoritmo.ordenar(produtos, criterioOrdenacao);
        List<Produto> filtrados = produtos.stream().filter(criterioFiltro::deveIncluir).collect(Collectors.toList());
        // Geração do HTML
        try (BufferedWriter out = new BufferedWriter(new FileWriter("saida.html"))) {
            out.write("<!DOCTYPE html><html>\n");
            out.write("<head><title>Relatório de produtos</title></head>\n");
            out.write("<body>\n");
            out.write("<ul>\n");
            for (Produto p : filtrados) {
                out.write("<li>" + p.formataParaImpressao() + "</li>\n");
            }
            out.write("</ul>\n");
            out.write(filtrados.size() + " produtos listados, de um total de " + produtos.size() + ".\n");
            out.write("</body></html>\n");
        }
    }
	public static void main(String[] args) {
        if (args.length < 5) {
            System.out.println("Uso:");
            System.out.println("java GeradorDeRelatorios <algoritmo> <criterio_ord> <criterio_filtro> <parametro filtro> <csv>");
            System.out.println("Onde:");
			System.out.println("\talgoritmo: 'quick' ou 'insertion'");
			System.out.println("\tcriterio de ordenação: 'preco_c', 'descricao_c', 'estoque_c', 'preco_d', 'descricao_d', 'estoque_d'");
			System.out.println("\tcriterio de filtragem: 'todos', 'estoque_menor_igual', 'categoria_igual', 'preco_intervalo' ou 'descricao_contem'"); 
			System.out.println("\tparâmetro de filtragem: argumentos adicionais necessários para a filtragem, caso não tenha, escrever qualquer coisa"); 
			System.out.println("\tnome do arquivo csv");
			System.out.println();
            return;
        }
        String algoritmoStr = args[0];
        String criterioStr = args[1];
        String filtroStr = args[2];
        String parametro = args[3];
        String caminho = args[4];
        List<Produto> produtos = new ArrayList<>();
        String linha;
        //Carrega produtos do .csv
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            br.readLine();
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",");
                int id = Integer.parseInt(campos[0].trim());
                String descricao = campos[1].trim();
                String categoria = campos[2].trim();
                int estoque = Integer.parseInt(campos[3].trim());
                double preco = Double.parseDouble(campos[4].trim());
                boolean negrito = Boolean.parseBoolean(campos[5].trim());
                boolean italico = Boolean.parseBoolean(campos[6].trim());
                String cor = campos[7].trim();
                Produto produto = new ProdutoPadrao(id, descricao, categoria, estoque, preco);
                if (negrito) produto = new ProdutoNegrito(produto);
                if (italico) produto = new ProdutoItalico(produto);
                if (cor != null && !cor.isEmpty()) produto = new ProdutoColorido(produto, cor);   
                produtos.add(produto);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV.");
            e.printStackTrace();
            return;
        }
        //Mapeamento da string para o algoritmo de ordenação
        AlgoritmoOrdenacao algoritmo = null;
        switch (algoritmoStr) {
            case "quick": algoritmo = new QuickSort(); break;
            case "insertion": algoritmo = new InsertionSort(); break;
            default:
                System.out.println("Algoritmo inválido");
                System.exit(1);
        }
        //Mapeamento da string para o criterio de ordenação
        CriterioOrdenacao criterio = null;
        switch (criterioStr) {
            case "descricao_c": criterio = new CriterioDescricao(true); break;
            case "preco_c": criterio = new CriterioPreco(true); break;
            case "estoque_c": criterio = new CriterioEstoque(true); break;
            case "descricao_d": criterio = new CriterioDescricao(false); break;
            case "preco_d": criterio = new CriterioPreco(false); break;
            case "estoque_d": criterio = new CriterioEstoque(false); break;
            default: 
                System.out.println("Critério inválido");
                System.exit(1);
        }
        //Mapeamento da string para o filtro
        CriterioFiltro filtro = null;
        switch(filtroStr){
            case "todos": filtro = new FiltroTodos(); break;
            case "estoque_menor_igual": filtro = new FiltroPorEstoqueMenorIgual(Integer.parseInt(parametro)); break;
            case "categoria_igual": filtro = new FiltroPorCategoria(parametro); break;
            case "preco_intervalo": 
                String[] partes = parametro.split(",");
                filtro = new FiltroPorIntervaloDePreco(Double.parseDouble(partes[0]), Double.parseDouble(partes[1])); break;
            case "descricao_contem": filtro = new FiltroPorDescricaoContendo(parametro); break;
            default: filtro = new FiltroTodos();
        }
        //Gera o relatório
        GeradorDeRelatorios gdr = new GeradorDeRelatorios(algoritmo, criterio, filtro);
        try {
            gdr.geraRelatorio(produtos);
            System.out.println("Relatório gerado com sucesso em: saida.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}