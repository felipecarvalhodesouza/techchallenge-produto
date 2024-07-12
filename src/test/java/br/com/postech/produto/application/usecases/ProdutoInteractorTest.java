package br.com.postech.produto.application.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void deveRegistrarProduto() {
		Produto produto = new Produto();
		when(produtoGateway.registrar(any(Produto.class))).thenReturn(produto);

		Produto result = produtoInteractor.registrar(produto);

		assertThat(result).isNotNull();
		verify(produtoGateway).registrar(produto);
	}

	@Test
	public void deveEditarProduto() {
		Produto produto = new Produto();
		when(produtoGateway.editar(any(Produto.class))).thenReturn(produto);

		Produto result = produtoInteractor.editar(produto);

		assertThat(result).isNotNull();
		verify(produtoGateway).editar(produto);
	}

	@Test
	public void deveRemoverProduto() {
		Long id = 1L;

		produtoInteractor.remover(id);

		verify(produtoGateway).remover(id);
	}

	@Test
	public void deveBuscarTodosOsProdutos() {
		List<Produto> produtos = Arrays.asList(new Produto(), new Produto());
		when(produtoGateway.getTodosOsProdutos()).thenReturn(produtos);

		List<Produto> result = produtoInteractor.getTodosOsProdutos();

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		verify(produtoGateway).getTodosOsProdutos();
	}

	@Test
	public void deveBuscarTodosOsProdutosPorTipo() {
		List<Produto> produtos = Arrays.asList(new Produto(), new Produto());
		when(produtoGateway.getTodosOsProdutosPor(eq(TipoProduto.LANCHE))).thenReturn(produtos);

		List<Produto> result = produtoInteractor.getTodosOsProdutosPor("Lanche");

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		verify(produtoGateway).getTodosOsProdutosPor(TipoProduto.LANCHE);
	}

	@Test
	public void deveRetornarListaVaziaAoBuscarTipoInvalido() {
		List<Produto> result = produtoInteractor.getTodosOsProdutosPor("Tipo Inválido");

		assertThat(result).isEmpty();
		verify(produtoGateway, never()).getTodosOsProdutosPor(TipoProduto.LANCHE);
	}

	@Test
	public void deveBuscarProdutosPorNome() {
		Produto produto = new Produto();
		when(produtoGateway.getProdutoPorNome("Produto Teste")).thenReturn(produto);

		Produto result = produtoInteractor.getProdutoPorNome("Produto Teste");

		assertThat(result).isNotNull();
		verify(produtoGateway).getProdutoPorNome("Produto Teste");
	}
}