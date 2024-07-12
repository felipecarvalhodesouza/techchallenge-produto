package br.com.postech.produto.main;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import br.com.postech.produto.application.gateway.ProdutoGateway;
import br.com.postech.produto.application.usecases.ProdutoInteractor;
import br.com.postech.produto.infraestrutura.gateway.ProdutoEntityMapper;

@SpringBootTest
@ActiveProfiles("test")
class ProdutoConfigTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        assertThat(context.getBean(ProdutoInteractor.class)).isNotNull();
        assertThat(context.getBean(ProdutoGateway.class)).isNotNull();
        assertThat(context.getBean(ProdutoEntityMapper.class)).isNotNull();
    }
}
