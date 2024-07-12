package br.com.postech.produto.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.postech.produto.application.gateway.ProdutoGateway;
import br.com.postech.produto.application.usecases.ProdutoInteractor;
import br.com.postech.produto.infraestrutura.gateway.ProdutoEntityMapper;
import br.com.postech.produto.infraestrutura.gateway.ProdutoRepositoryGateway;
import br.com.postech.produto.infraestrutura.persistence.ProdutoRepository;

@Configuration
public class ProdutoConfig {

	@Bean
	ProdutoInteractor createProdutoUseCase(ProdutoGateway produtoGateway) {
		return new ProdutoInteractor(produtoGateway);
	}

	@Bean
	ProdutoGateway ProdutoGateway(ProdutoRepository produtoRepository, ProdutoEntityMapper mapper) {
		return new ProdutoRepositoryGateway(produtoRepository, mapper);
	}

	@Bean
	ProdutoEntityMapper ProdutoEntityMapper() {
		return new ProdutoEntityMapper();
	}
}
