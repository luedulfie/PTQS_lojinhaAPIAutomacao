package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoComumComOValorIgualA(double valor) {
        ProdutoPojo produto = new ProdutoPojo();

        produto.setProdutoNome("Playstation 4");
        produto.setProdutoValor(valor);

        List<String> cores = new ArrayList<>();
        cores.add("preto");
        cores.add("branco");
        produto.setProdutoCores(cores);

        produto.setProdutoUrlMock("");

        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo primeiroComponente = new ComponentePojo();
        primeiroComponente.setComponenteNome("Controle");
        primeiroComponente.setComponenteQuantidade(1);
        componentes.add(primeiroComponente);
        produto.setComponentes(componentes);

        ComponentePojo segundoComponente = new ComponentePojo();
        segundoComponente.setComponenteNome("Memory card");
        segundoComponente.setComponenteQuantidade(2);
        componentes.add(segundoComponente);
        produto.setComponentes(componentes);

        return produto;
    }
}
