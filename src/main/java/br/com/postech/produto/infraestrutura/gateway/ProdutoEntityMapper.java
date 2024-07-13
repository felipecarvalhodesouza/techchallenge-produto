package br.com.postech.produto.infraestrutura.gateway;

import java.util.UUID;

import br.com.postech.produto.domain.entity.Produto;
import br.com.postech.produto.infraestrutura.persistence.ProdutoEntity;

public class ProdutoEntityMapper {

	public ProdutoEntity toEntity(Produto produtoDomain) {
		ProdutoEntity entity = new ProdutoEntity();
		entity.setId(produtoDomain.getId().toString());
		if(produtoDomain.getNomeProduto() != null) {
			entity.setNomeProduto(produtoDomain.getNomeProduto());
		}
		if(produtoDomain.getTipoProduto() != null) {
			entity.setTipoProduto(produtoDomain.getTipoProduto());
		}
		if(produtoDomain.getValor() != 0) {
			entity.setValor(produtoDomain.getValor());
		}
		return entity;
	}

	public Produto toDomainObject(ProdutoEntity entity) {
		Produto produto = new Produto();
		produto.setId(UUID.fromString(entity.getId()));
		if(entity.getNomeProduto() != null) {
			produto.setNomeProduto(entity.getNomeProduto());
		}
		if(entity.getTipoProduto() != null) {
			produto.setTipoProduto(entity.getTipoProduto());
		}
		if(entity.getValor() != 0) {
			produto.setValor(entity.getValor());
		}
		return produto;
	}
}
