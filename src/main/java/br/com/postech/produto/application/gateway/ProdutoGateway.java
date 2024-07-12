package br.com.postech.produto.application.gateway;

import java.util.List;

import br.com.postech.produto.domain.entity.Produto;
import br.com.postech.produto.domain.enumeration.TipoProduto;

public interface ProdutoGateway {

	Produto registrar(Produto produto);

	Produto editar(Produto produto);

	void remover(Long id);

	List<Produto> getTodosOsProdutos();

	List<Produto> getTodosOsProdutosPor(TipoProduto tipoProduto);
	
	Produto getProdutoPorNome(String nomeProduto);
}
