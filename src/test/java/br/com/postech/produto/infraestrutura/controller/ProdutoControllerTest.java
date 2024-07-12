package br.com.postech.produto.infraestrutura.controller;


import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import br.com.postech.produto.application.usecases.ProdutoInteractor;
import br.com.postech.produto.domain.entity.Produto;
import io.restassured.common.mapper.TypeRef;

class ProdutoControllerTest {

	@Mock
	private ProdutoInteractor produtoInteractor;

	@InjectMocks
	private ProdutoController produtoController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		standaloneSetup(produtoController);
	}

	@Test
	void deveRegistrarProduto() {
		Produto produto = new Produto();
		produto.setNomeProduto("Produto Teste");
		when(produtoInteractor.registrar(any(Produto.class))).thenReturn(produto);

		Produto result = given()
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.body(produto)
						.when()
							.post("/produtos")
						.then()
							.statusCode(HttpStatus.CREATED.value())
							.extract()
							.as(Produto.class);

		assertThat(result).isNotNull();
		assertThat(result.getNomeProduto()).isEqualTo("Produto Teste");
		verify(produtoInteractor).registrar(any(Produto.class));
	}

	@Test
	void deveEditarProduto() {
		Produto produto = new Produto();
		produto.setNomeProduto("Produto Editado");
		when(produtoInteractor.editar(any(Produto.class))).thenReturn(produto);

		Produto result = given()
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.body(produto)
						.when()
							.put("/produtos")
						.then()
							.statusCode(HttpStatus.OK.value())
							.extract()
							.as(Produto.class);

		assertThat(result).isNotNull();
		assertThat(result.getNomeProduto()).isEqualTo("Produto Editado");
		verify(produtoInteractor).editar(any(Produto.class));
	}

	@Test
	void deveRemoverProduto() {
		Long id = 1L;

		given()
		.when()
			.delete("/produtos/{id}", String.valueOf(id))
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());

		verify(produtoInteractor).remover(id);
	}

	@Test
	void deveBuscarTodosOsProdutos() {
		List<Produto> produtos = Arrays.asList(new Produto(), new Produto());
		when(produtoInteractor.getTodosOsProdutos()).thenReturn(produtos);

		List<Produto> result = given()
							  .when()
							  	.get("/produtos/lista")
							  .then()
							  	.statusCode(HttpStatus.OK.value())
							  	.extract()
							  	.as(new TypeRef<List<Produto>>() {});

		assertThat(result).isNotNull().hasSize(2);
		verify(produtoInteractor).getTodosOsProdutos();
	}

	@Test
	void deveBuscarTodosOsProdutosPorTipo() {
		List<Produto> produtos = Arrays.asList(new Produto(), new Produto());
		when(produtoInteractor.getTodosOsProdutosPor("Lanche")).thenReturn(produtos);

		List<Produto> result = given()
							  .when()
							  	.get("/produtos/lista/{tipoProduto}", "Lanche")
							  .then()
							  	.statusCode(HttpStatus.OK.value())
							  	.extract()
							  	.as(new TypeRef<List<Produto>>() {});

		assertThat(result).isNotNull().hasSize(2);
		verify(produtoInteractor).getTodosOsProdutosPor("Lanche");
	}

	@Test
	void deveBuscarProdutoPorNome() {
		Produto produto = new Produto();
		produto.setNomeProduto("Produto Teste");
		when(produtoInteractor.getProdutoPorNome("Produto Teste")).thenReturn(produto);

		Produto result = given()
							.queryParam("nomeProduto", "Produto Teste")
						.when()
							.get("/produtos/")
						.then()
							.statusCode(HttpStatus.OK.value())
							.extract()
							.as(Produto.class);

		assertThat(result).isNotNull();
		assertThat(result.getNomeProduto()).isEqualTo("Produto Teste");
		verify(produtoInteractor).getProdutoPorNome("Produto Teste");
	}
	
	@Test
	void deveRetornarNullQuandoProdutoInexistente() {
		Produto produto = new Produto();
		produto.setNomeProduto("Produto Teste");
		when(produtoInteractor.getProdutoPorNome("Produto Teste")).thenReturn(null);

		byte[] result = given()
							.queryParam("nomeProduto", "Produto Teste")
						.when()
							.get("/produtos/")
						.then()
							.statusCode(HttpStatus.OK.value())
							.extract()
							.asByteArray();

		assertThat(result).isEmpty();
	}
}
