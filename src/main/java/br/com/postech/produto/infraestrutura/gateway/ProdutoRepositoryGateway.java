package br.com.postech.produto.infraestrutura.gateway;

import java.util.List;
import java.util.stream.Collectors;

import br.com.postech.produto.application.gateway.ProdutoGateway;
import br.com.postech.produto.domain.entity.Produto;
import br.com.postech.produto.domain.enumeration.TipoProduto;
import br.com.postech.produto.infraestrutura.persistence.ProdutoEntity;
import br.com.postech.produto.infraestrutura.persistence.ProdutoRepository;

public class ProdutoRepositoryGateway implements ProdutoGateway{

	private final ProdutoRepository produtoRepository;
	private ProdutoEntityMapper mapper;

	public ProdutoRepositoryGateway(ProdutoRepository produtoRepository, ProdutoEntityMapper mapper) {
		this.produtoRepository = produtoRepository;
		this.mapper = mapper;
	}

	@Override
	public Produto registrar(Produto produto) {
		ProdutoEntity entity = produtoRepository.save(mapper.toEntity(produto));
		return mapper.toDomainObject(entity);
	}

	@Override
	public Produto editar(Produto produto) {
		ProdutoEntity entity = produtoRepository.save(mapper.toEntity(produto));
		return mapper.toDomainObject(entity);
	}

	@Override
	public void remover(Long id) {
		produtoRepository.deleteById(id);
	}

	@Override
	public List<Produto> getTodosOsProdutos() {
		return produtoRepository.findAll()
								.stream()
								.map( entity -> mapper.toDomainObject(entity))
								.collect(Collectors.toList());
	}

	@Override
	public List<Produto> getTodosOsProdutosPor(TipoProduto tipoProduto) {
		return produtoRepository.findAllByTipoProduto(tipoProduto)
								.stream()
								.map( entity -> mapper.toDomainObject(entity))
								.collect(Collectors.toList());
	}

	@Override
	public Produto getProdutoPorNome(String nomeProduto) {
		List<ProdutoEntity> list = produtoRepository.getByNomeProduto(nomeProduto);
		
		if(list.isEmpty()) {
			return null;
		}
		return mapper.toDomainObject(list.get(0));
	}
}
