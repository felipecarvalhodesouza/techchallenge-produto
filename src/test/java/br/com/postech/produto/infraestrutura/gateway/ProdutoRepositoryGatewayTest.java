package br.com.postech.produto.infraestrutura.gateway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
	
	private Produto produto;
	private ProdutoEntity produtoEntity;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
        produto = new Produto();
        produto.setId(UUID.randomUUID());

        produtoEntity = new ProdutoEntity();
        produtoEntity.setId(produto.getId().toString());
        produtoEntity.setNomeProduto("Nome Antigo");
        produtoEntity.setTipoProduto(TipoProduto.BEBIDA);
        produtoEntity.setValor(100.0);
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
		produto.setId(UUID.fromString("6ca9fa9f-9450-4438-83ed-8dc25b56e2c4"));
		produto.setNomeProduto("Produto Editado");

		ProdutoEntity entity = new ProdutoEntity();
		entity.setId("6ca9fa9f-9450-4438-83ed-8dc25b56e2c4");
		entity.setNomeProduto("Produto Editado");

		when(mapper.toEntity(produto)).thenReturn(entity);
		when(produtoRepository.findById(produto.getId().toString())).thenReturn(entity);
		when(produtoRepository.save(entity)).thenReturn(entity);
		when(mapper.toDomainObject(entity)).thenReturn(produto);

		Produto result = produtoRepositoryGateway.editar(produto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(UUID.fromString("6ca9fa9f-9450-4438-83ed-8dc25b56e2c4"));
		assertThat(result.getNomeProduto()).isEqualTo("Produto Editado");
		verify(produtoRepository).save(entity);
		verify(mapper).toDomainObject(entity);
	}
	
	@Test
    void deveEditarProdutoComNomeNulo() {
        produto.setNomeProduto(null);
        produto.setTipoProduto(TipoProduto.ACOMPANHAMENTO);
        produto.setValor(200.0);

        when(produtoRepository.findById(produto.getId().toString())).thenReturn(produtoEntity);
        when(produtoRepository.save(produtoEntity)).thenAnswer(f -> f.getArgument(0));
        when(mapper.toDomainObject(produtoEntity)).thenCallRealMethod();

        Produto result = produtoRepositoryGateway.editar(produto);

        assertThat(result).isNotNull();
        assertThat(result.getNomeProduto()).isEqualTo("Nome Antigo");
        assertThat(result.getTipoProduto()).isEqualTo(TipoProduto.ACOMPANHAMENTO);
        assertThat(result.getValor()).isEqualTo(200.0);

        verify(produtoRepository).findById(produto.getId().toString());
        verify(produtoRepository).save(produtoEntity);
        verify(mapper).toDomainObject(produtoEntity);
    }

    @Test
    void deveEditarProdutoComTipoNulo() {
        produto.setNomeProduto("Nome Atualizado");
        produto.setTipoProduto(null);
        produto.setValor(200.0);

        when(produtoRepository.findById(produto.getId().toString())).thenReturn(produtoEntity);
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(produtoEntity);
        when(mapper.toDomainObject(produtoEntity)).thenCallRealMethod();

        Produto result = produtoRepositoryGateway.editar(produto);

        assertThat(result).isNotNull();
        assertThat(result.getNomeProduto()).isEqualTo("Nome Atualizado");
        assertThat(result.getTipoProduto()).isEqualTo(TipoProduto.BEBIDA);
        assertThat(result.getValor()).isEqualTo(200.0);

        verify(produtoRepository).findById(produto.getId().toString());
        verify(produtoRepository).save(produtoEntity);
        verify(mapper).toDomainObject(produtoEntity);
    }

    @Test
    void deveEditarProdutoComValorZero() {
        produto.setNomeProduto("Nome Atualizado");
        produto.setTipoProduto(TipoProduto.ACOMPANHAMENTO);
        produto.setValor(0);

        when(produtoRepository.findById(produto.getId().toString())).thenReturn(produtoEntity);
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(produtoEntity);
        when(mapper.toDomainObject(produtoEntity)).thenCallRealMethod();

        Produto result = produtoRepositoryGateway.editar(produto);

        assertThat(result).isNotNull();
        assertThat(result.getNomeProduto()).isEqualTo("Nome Atualizado");
        assertThat(result.getTipoProduto()).isEqualTo(TipoProduto.ACOMPANHAMENTO);
        assertThat(result.getValor()).isEqualTo(100.0);

        verify(produtoRepository).findById(produto.getId().toString());
        verify(produtoRepository).save(produtoEntity);
        verify(mapper).toDomainObject(produtoEntity);
    }

	@Test
	void deveRemoverProduto() {
		String id = "b5132c97-b776-4f1c-98b8-042583e15a04";

		produtoRepositoryGateway.remover(id);

		verify(produtoRepository).deleteById(id);
	}

	@Test
	void deveBuscarTodosOsProdutos() {
		ProdutoEntity entity1 = new ProdutoEntity();
		entity1.setId("6d820bfe-8e1f-4144-96f7-6f2029789eb8");
		entity1.setNomeProduto("Produto 1");

		ProdutoEntity entity2 = new ProdutoEntity();
		entity2.setId("99826795-39dc-4a21-99df-8896f6107980");
		entity2.setNomeProduto("Produto 2");

		List<ProdutoEntity> entities = Arrays.asList(entity1, entity2);

		when(produtoRepository.findAll()).thenReturn(entities);
		when(mapper.toDomainObject(entity1)).thenReturn(getNovoProduto(UUID.fromString("6d820bfe-8e1f-4144-96f7-6f2029789eb8"), "Produto 1"));
		when(mapper.toDomainObject(entity2)).thenReturn(getNovoProduto(UUID.fromString("99826795-39dc-4a21-99df-8896f6107980"), "Produto 2"));

		List<Produto> result = produtoRepositoryGateway.getTodosOsProdutos();

		assertThat(result).isNotNull().hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(UUID.fromString("6d820bfe-8e1f-4144-96f7-6f2029789eb8"));
		assertThat(result.get(0).getNomeProduto()).isEqualTo("Produto 1");
		assertThat(result.get(1).getId()).isEqualTo(UUID.fromString("99826795-39dc-4a21-99df-8896f6107980"));
		assertThat(result.get(1).getNomeProduto()).isEqualTo("Produto 2");
		verify(produtoRepository).findAll();
		verify(mapper, times(2)).toDomainObject(any());
	}

	@Test
	void deveBuscarTodosOsProdutosPorTipo() {
		TipoProduto tipoProduto = TipoProduto.LANCHE;

		ProdutoEntity entity1 = new ProdutoEntity();
		entity1.setId("6d820bfe-8e1f-4144-96f7-6f2029789eb8");
		entity1.setNomeProduto("Produto 1");
		entity1.setTipoProduto(tipoProduto);

		ProdutoEntity entity2 = new ProdutoEntity();
		entity2.setId("99826795-39dc-4a21-99df-8896f6107980");
		entity2.setNomeProduto("Produto 2");
		entity2.setTipoProduto(tipoProduto);

		List<ProdutoEntity> entities = Arrays.asList(entity1, entity2);

		when(produtoRepository.findAllByTipoProduto(tipoProduto)).thenReturn(entities);
		when(mapper.toDomainObject(entity1)).thenReturn(getNovoProduto(UUID.fromString("6d820bfe-8e1f-4144-96f7-6f2029789eb8"), "Produto 1", tipoProduto));
		when(mapper.toDomainObject(entity2)).thenReturn(getNovoProduto(UUID.fromString("99826795-39dc-4a21-99df-8896f6107980"), "Produto 2", tipoProduto));

		List<Produto> result = produtoRepositoryGateway.getTodosOsProdutosPor(tipoProduto);

		assertThat(result).isNotNull().hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(UUID.fromString("6d820bfe-8e1f-4144-96f7-6f2029789eb8"));
		assertThat(result.get(0).getNomeProduto()).isEqualTo("Produto 1");
		assertThat(result.get(0).getTipoProduto()).isEqualTo(tipoProduto);
		assertThat(result.get(1).getId()).isEqualTo(UUID.fromString("99826795-39dc-4a21-99df-8896f6107980"));
		assertThat(result.get(1).getNomeProduto()).isEqualTo("Produto 2");
		assertThat(result.get(1).getTipoProduto()).isEqualTo(tipoProduto);
		verify(produtoRepository).findAllByTipoProduto(tipoProduto);
		verify(mapper, times(2)).toDomainObject(any());
	}

	@Test
	void deveBuscarProdutoPorNome() {
		String nomeProduto = "Produto 1";

		ProdutoEntity entity = new ProdutoEntity();
		entity.setId("6d820bfe-8e1f-4144-96f7-6f2029789eb8");
		entity.setNomeProduto(nomeProduto);

		when(produtoRepository.getByNomeProduto(nomeProduto)).thenReturn(Arrays.asList(entity));
		when(mapper.toDomainObject(entity)).thenReturn(getNovoProduto(UUID.fromString("6d820bfe-8e1f-4144-96f7-6f2029789eb8"), nomeProduto));

		Produto result = produtoRepositoryGateway.getProdutoPorNome(nomeProduto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(UUID.fromString("6d820bfe-8e1f-4144-96f7-6f2029789eb8"));
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

	private Produto getNovoProduto(UUID id, String nomeProduto, TipoProduto tipoProduto) {
		Produto produto = new Produto();
		produto.setId(id);
		produto.setNomeProduto(nomeProduto);
		produto.setTipoProduto(tipoProduto);
		return produto;
	}

	private Produto getNovoProduto(UUID id, String nomeProduto) {
		return getNovoProduto(id, nomeProduto, null);
	}
}