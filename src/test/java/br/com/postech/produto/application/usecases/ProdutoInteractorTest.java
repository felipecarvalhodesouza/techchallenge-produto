package br.com.postech.produto.application.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import br.com.postech.produto.application.gateway.ProdutoGateway;
import br.com.postech.produto.domain.entity.Produto;
import br.com.postech.produto.domain.enumeration.TipoProduto;

@ActiveProfiles("test")
class ProdutoInteractorTest {

	@Mock
	private ProdutoGateway produtoGateway;

	@InjectMocks
	private ProdutoInteractor produtoInteractor;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveRegistrarProduto() {
		Produto produto = new Produto();
		when(produtoGateway.registrar(any(Produto.class))).thenReturn(produto);

		Produto result = produtoInteractor.registrar(produto);

		assertThat(result).isNotNull();
		verify(produtoGateway).registrar(produto);
	}

	@Test
	void deveEditarProduto() {
		Produto produto = new Produto();
		when(produtoGateway.editar(any(Produto.class))).thenReturn(produto);

		Produto result = produtoInteractor.editar(produto);

		assertThat(result).isNotNull();
		verify(produtoGateway).editar(produto);
	}

	@Test
	void deveRemoverProduto() {
		String id = "b5132c97-b776-4f1c-98b8-042583e15a04";

		produtoInteractor.remover(id);

		verify(produtoGateway).remover(id);
	}

	@Test
	void deveBuscarTodosOsProdutos() {
		List<Produto> produtos = Arrays.asList(new Produto(), new Produto());
		when(produtoGateway.getTodosOsProdutos()).thenReturn(produtos);

		List<Produto> result = produtoInteractor.getTodosOsProdutos();

		assertThat(result).isNotNull().hasSize(2);
		verify(produtoGateway).getTodosOsProdutos();
	}

	@Test
	void deveBuscarTodosOsProdutosPorTipo() {
		List<Produto> produtos = Arrays.asList(new Produto(), new Produto());
		when(produtoGateway.getTodosOsProdutosPor(TipoProduto.LANCHE)).thenReturn(produtos);

		List<Produto> result = produtoInteractor.getTodosOsProdutosPor("Lanche");

		assertThat(result).isNotNull().hasSize(2);
		verify(produtoGateway).getTodosOsProdutosPor(TipoProduto.LANCHE);
	}

	@Test
	void deveRetornarListaVaziaAoBuscarTipoInvalido() {
		List<Produto> result = produtoInteractor.getTodosOsProdutosPor("Tipo Inválido");

		assertThat(result).isEmpty();
		verify(produtoGateway, never()).getTodosOsProdutosPor(TipoProduto.LANCHE);
	}

	@Test
	void deveBuscarProdutosPorNome() {
		Produto produto = new Produto();
		when(produtoGateway.getProdutoPorNome("Produto Teste")).thenReturn(produto);

		Produto result = produtoInteractor.getProdutoPorNome("Produto Teste");

		assertThat(result).isNotNull();
		verify(produtoGateway).getProdutoPorNome("Produto Teste");
	}
}
