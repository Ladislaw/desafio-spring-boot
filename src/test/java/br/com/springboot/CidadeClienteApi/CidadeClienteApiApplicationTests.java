package br.com.springboot.CidadeClienteApi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.springboot.CidadeClienteApi.controller.CidadeController;
import br.com.springboot.CidadeClienteApi.controller.ClienteController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CidadeClienteApiApplicationTests {

	@Autowired
	private CidadeController cidadeController;

	@Autowired
	private ClienteController clienteController;
	
	@Test
	void contextLoads() {
		assertThat(cidadeController).isNotNull();
		assertThat(clienteController).isNotNull();
	}

}
