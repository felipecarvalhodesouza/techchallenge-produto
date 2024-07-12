package br.com.postech.produto.infraestrutura.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.postech.produto.domain.enumeration.TipoProduto;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long>{

	List<ProdutoEntity> findAllByTipoProduto(TipoProduto tipoProduto);

	List<ProdutoEntity> getByNomeProduto(String nomeProduto);
}
