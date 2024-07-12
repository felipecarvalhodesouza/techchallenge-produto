package br.com.postech.produto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class ProdutoApplicationIT {

	@Test
	void main() {
		boolean isRunning = true;
		try {
			ProdutoApplication.main(new String[] {});
		} catch (Exception e) {
			isRunning = false;
		}

		assertThat(isRunning).isTrue();
	}
}
