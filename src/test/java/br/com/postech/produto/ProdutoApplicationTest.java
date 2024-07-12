package br.com.postech.produto;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class ProdutoApplicationIT {

	@Test
	public void main() {
		ProdutoApplication.main(new String[] {});
	}
}
