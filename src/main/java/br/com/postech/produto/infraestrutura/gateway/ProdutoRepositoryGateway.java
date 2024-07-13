package br.com.postech.produto.infraestrutura.gateway;

import java.util.List;
import java.util.UUID;

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
		produto.setId(UUID.randomUUID());
		ProdutoEntity entity = produtoRepository.save(mapper.toEntity(produto));
		return mapper.toDomainObject(entity);
	}

	@Override
	public Produto editar(Produto produto) {
		ProdutoEntity registroPersistido = produtoRepository.findById(produto.getId().toString());
		
		if (produto.getNomeProduto() != null) {
			registroPersistido.setNomeProduto(produto.getNomeProduto());
		}
		if (produto.getTipoProduto() != null) {
			registroPersistido.setTipoProduto(produto.getTipoProduto());
		}
		if (produto.getValor() != 0) {
			registroPersistido.setValor(produto.getValor());
		}	
		
		ProdutoEntity entity = produtoRepository.save(registroPersistido);
		return mapper.toDomainObject(entity);
	}

	@Override
	public void remover(String id) {
		produtoRepository.deleteById(id);
	}

	@Override
	public List<Produto> getTodosOsProdutos() {
		return produtoRepository.findAll()
								.stream()
								.map( entity -> mapper.toDomainObject(entity))
								.toList();
	}

	@Override
	public List<Produto> getTodosOsProdutosPor(TipoProduto tipoProduto) {
		return produtoRepository.findAllByTipoProduto(tipoProduto)
								.stream()
								.map( entity -> mapper.toDomainObject(entity))
								.toList();
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
