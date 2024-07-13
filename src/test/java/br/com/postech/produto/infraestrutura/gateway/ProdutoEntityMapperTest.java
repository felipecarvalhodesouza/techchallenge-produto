package br.com.postech.produto.infraestrutura.gateway;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import br.com.postech.produto.domain.entity.Produto;
import br.com.postech.produto.domain.enumeration.TipoProduto;
import br.com.postech.produto.infraestrutura.persistence.ProdutoEntity;

class ProdutoEntityMapperTest {

    @InjectMocks
    private ProdutoEntityMapper produtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConverterDomainToEntity() {
        Produto produtoDomain = new Produto();
        produtoDomain.setId(UUID.randomUUID());
        produtoDomain.setNomeProduto("Produto Teste");
        produtoDomain.setTipoProduto(TipoProduto.LANCHE);
        produtoDomain.setValor(10.0);

        ProdutoEntity entity = produtoMapper.toEntity(produtoDomain);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(produtoDomain.getId().toString());
        assertThat(entity.getNomeProduto()).isEqualTo(produtoDomain.getNomeProduto());
        assertThat(entity.getTipoProduto()).isEqualTo(produtoDomain.getTipoProduto());
        assertThat(entity.getValor()).isEqualTo(produtoDomain.getValor());
    }

    @Test
    void deveConverterEntityToDomain() {
        ProdutoEntity entity = new ProdutoEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setNomeProduto("Produto Teste");
        entity.setTipoProduto(TipoProduto.LANCHE);
        entity.setValor(10.0);

        Produto produto = produtoMapper.toDomainObject(entity);

        assertThat(produto).isNotNull();
        assertThat(produto.getId().toString()).isEqualTo(entity.getId());
        assertThat(produto.getNomeProduto()).isEqualTo(entity.getNomeProduto());
        assertThat(produto.getTipoProduto()).isEqualTo(entity.getTipoProduto());
        assertThat(produto.getValor()).isEqualTo(entity.getValor());
    }
}
