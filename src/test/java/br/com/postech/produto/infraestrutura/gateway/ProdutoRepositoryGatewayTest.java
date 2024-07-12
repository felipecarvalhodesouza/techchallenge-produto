package br.com.postech.produto.infraestrutura.gateway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.postech.produto.domain.entity.Produto;
import br.com.postech.produto.domain.enumeration.TipoProduto;
import br.com.postech.produto.infraestrutura.persistence.ProdutoEntity;
import br.com.postech.produto.infraestrutura.persistence.ProdutoRepository;

class ProdutoRepositoryGatewayTest {

	@Mock
	private ProdutoRepository produtoRepository;

	@Mock
	private ProdutoEntityMapper mapper;

	@InjectMocks
	private ProdutoRepositoryGateway produtoRepositoryGateway;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveRegistrarProduto() {
		Produto produto = new Produto();
		produto.setNomeProduto("Produto Teste");

		ProdutoEntity entity = new ProdutoEntity();
		entity.setNomeProduto("Produto Teste");

		when(mapper.toEntity(produto)).thenReturn(entity);
		when(produtoRepository.save(entity)).thenReturn(entity);
		when(mapper.toDomainObject(entity)).thenReturn(produto);

		Produto result = produtoRepositoryGateway.registrar(produto);

		assertThat(result).isNotNull();
		assertThat(result.getNomeProduto()).isEqualTo("Produto Teste");
		verify(mapper).toEntity(produto);
		verify(produtoRepository).save(entity);
		verify(mapper).toDomainObject(entity);
	}

	@Test
	void deveEditarProduto() {
		Produto produto = new Produto();
		produto.setId(1L);
		produto.setNomeProduto("Produto Editado");

		ProdutoEntity entity = new ProdutoEntity();
		entity.setId(1L);
		entity.setNomeProduto("Produto Editado");

		when(mapper.toEntity(produto)).thenReturn(entity);
		when(produtoRepository.save(entity)).thenReturn(entity);
		when(mapper.toDomainObject(entity)).thenReturn(produto);

		Produto result = produtoRepositoryGateway.editar(produto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(1L);
		assertThat(result.getNomeProduto()).isEqualTo("Produto Editado");
		verify(mapper).toEntity(produto);
		verify(produtoRepository).save(entity);
		verify(mapper).toDomainObject(entity);
	}

	@Test
	void deveRemoverProduto() {
		Long id = 1L;

		produtoRepositoryGateway.remover(id);

		verify(produtoRepository).deleteById(id);
	}

	@Test
	void deveBuscarTodosOsProdutos() {
		ProdutoEntity entity1 = new ProdutoEntity();
		entity1.setId(1L);
		entity1.setNomeProduto("Produto 1");

		ProdutoEntity entity2 = new ProdutoEntity();
		entity2.setId(2L);
		entity2.setNomeProduto("Produto 2");

		List<ProdutoEntity> entities = Arrays.asList(entity1, entity2);

		when(produtoRepository.findAll()).thenReturn(entities);
		when(mapper.toDomainObject(entity1)).thenReturn(getNovoProduto(1L, "Produto 1"));
		when(mapper.toDomainObject(entity2)).thenReturn(getNovoProduto(2L, "Produto 2"));

		List<Produto> result = produtoRepositoryGateway.getTodosOsProdutos();

		assertThat(result).isNotNull().hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(1L);
		assertThat(result.get(0).getNomeProduto()).isEqualTo("Produto 1");
		assertThat(result.get(1).getId()).isEqualTo(2L);
		assertThat(result.get(1).getNomeProduto()).isEqualTo("Produto 2");
		verify(produtoRepository).findAll();
		verify(mapper, times(2)).toDomainObject(any());
	}

	@Test
	void deveBuscarTodosOsProdutosPorTipo() {
		TipoProduto tipoProduto = TipoProduto.LANCHE;

		ProdutoEntity entity1 = new ProdutoEntity();
		entity1.setId(1L);
		entity1.setNomeProduto("Produto 1");
		entity1.setTipoProduto(tipoProduto);

		ProdutoEntity entity2 = new ProdutoEntity();
		entity2.setId(2L);
		entity2.setNomeProduto("Produto 2");
		entity2.setTipoProduto(tipoProduto);

		List<ProdutoEntity> entities = Arrays.asList(entity1, entity2);

		when(produtoRepository.findAllByTipoProduto(tipoProduto)).thenReturn(entities);
		when(mapper.toDomainObject(entity1)).thenReturn(getNovoProduto(1L, "Produto 1", tipoProduto));
		when(mapper.toDomainObject(entity2)).thenReturn(getNovoProduto(2L, "Produto 2", tipoProduto));

		List<Produto> result = produtoRepositoryGateway.getTodosOsProdutosPor(tipoProduto);

		assertThat(result).isNotNull().hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(1L);
		assertThat(result.get(0).getNomeProduto()).isEqualTo("Produto 1");
		assertThat(result.get(0).getTipoProduto()).isEqualTo(tipoProduto);
		assertThat(result.get(1).getId()).isEqualTo(2L);
		assertThat(result.get(1).getNomeProduto()).isEqualTo("Produto 2");
		assertThat(result.get(1).getTipoProduto()).isEqualTo(tipoProduto);
		verify(produtoRepository).findAllByTipoProduto(tipoProduto);
		verify(mapper, times(2)).toDomainObject(any());
	}

	@Test
	void deveBuscarProdutoPorNome() {
		String nomeProduto = "Produto 1";

		ProdutoEntity entity = new ProdutoEntity();
		entity.setId(1L);
		entity.setNomeProduto(nomeProduto);

		when(produtoRepository.getByNomeProduto(nomeProduto)).thenReturn(Arrays.asList(entity));
		when(mapper.toDomainObject(entity)).thenReturn(getNovoProduto(1L, nomeProduto));

		Produto result = produtoRepositoryGateway.getProdutoPorNome(nomeProduto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(1L);
		assertThat(result.getNomeProduto()).isEqualTo(nomeProduto);
		verify(produtoRepository).getByNomeProduto(nomeProduto);
		verify(mapper).toDomainObject(entity);
	}
	
	@Test
	void deveRetornarNullQuandoProdutoInexistente() {
		String nomeProduto = "Produto 1";

		when(produtoRepository.getByNomeProduto(nomeProduto)).thenReturn(Collections.emptyList());

		Produto result = produtoRepositoryGateway.getProdutoPorNome(nomeProduto);
		assertThat(result).isNull();
	}

	private Produto getNovoProduto(Long id, String nomeProduto, TipoProduto tipoProduto) {
		Produto produto = new Produto();
		produto.setId(id);
		produto.setNomeProduto(nomeProduto);
		produto.setTipoProduto(tipoProduto);
		return produto;
	}

	private Produto getNovoProduto(Long id, String nomeProduto) {
		return getNovoProduto(id, nomeProduto, null);
	}
}