package br.com.postech.produto.application.usecases;

import java.util.Collections;
import java.util.List;

import br.com.postech.produto.application.gateway.ProdutoGateway;
import br.com.postech.produto.domain.entity.Produto;
import br.com.postech.produto.domain.enumeration.TipoProduto;

public class ProdutoInteractor {

	private final ProdutoGateway produtoGateway;

	public ProdutoInteractor(ProdutoGateway produtoGateway) {
		this.produtoGateway = produtoGateway;
	}

	public Produto registrar(Produto produto) {
		return produtoGateway.registrar(produto);
	}

	public Produto editar(Produto produto) {
		return produtoGateway.editar(produto);
	}

	public void remover(Long id) {
		produtoGateway.remover(id);
	}

	public List<Produto> getTodosOsProdutos() {
		return produtoGateway.getTodosOsProdutos();
	}

	public List<Produto> getTodosOsProdutosPor(String tipoProduto) {
		TipoProduto tipoProdutoEnum = TipoProduto.obterPorDescricao(tipoProduto);
		if (tipoProdutoEnum == null) {
			return Collections.emptyList();
		}
		return produtoGateway.getTodosOsProdutosPor(tipoProdutoEnum);
	}
	
	public Produto getProdutoPorNome(String nomeProduto) {
		return produtoGateway.getProdutoPorNome(nomeProduto);
	}
}
