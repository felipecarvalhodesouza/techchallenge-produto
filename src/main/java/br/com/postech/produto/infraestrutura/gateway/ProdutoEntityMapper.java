package br.com.postech.produto.infraestrutura.gateway;

import org.springframework.beans.BeanUtils;

import br.com.postech.produto.domain.entity.Produto;
import br.com.postech.produto.infraestrutura.persistence.ProdutoEntity;

public class ProdutoEntityMapper {

	public ProdutoEntity toEntity(Produto produtoDomain) {
		ProdutoEntity entity = new ProdutoEntity();
		BeanUtils.copyProperties(produtoDomain, entity);
		return entity;
	}

	public Produto toDomainObject(ProdutoEntity entity) {
		Produto produto = new Produto();
		BeanUtils.copyProperties(entity, produto);
		return produto;
	}
}
