package br.com.postech.produto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class ProdutoApplicationIT {

	@Test
	void main() {
		ProdutoApplication.main(new String[] {});
		assertThat(true).isTrue();
	}
}
