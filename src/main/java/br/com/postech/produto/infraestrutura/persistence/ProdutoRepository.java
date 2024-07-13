package br.com.postech.produto.infraestrutura.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.postech.produto.domain.enumeration.TipoProduto;

public interface ProdutoRepository extends MongoRepository<ProdutoEntity, Long>{

	ProdutoEntity findById(String id);
	
	List<ProdutoEntity> findAllByTipoProduto(TipoProduto tipoProduto);

	List<ProdutoEntity> getByNomeProduto(String nomeProduto);

	void deleteById(String id);
}
