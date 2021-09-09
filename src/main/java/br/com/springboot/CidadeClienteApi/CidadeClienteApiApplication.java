package br.com.springboot.CidadeClienteApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"br.com.springboot.CidadeClienteApi"})
@EntityScan({"br.com.springboot.CidadeClienteApi.model"})
@EnableJpaRepositories({"br.com.springboot.CidadeClienteApi.repository"})
public class CidadeClienteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CidadeClienteApiApplication.class, args);
	}

}
