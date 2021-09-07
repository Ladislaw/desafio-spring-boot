package br.com.springboot.CidadeClienteApi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

	private String nomeCompleto;
	private String sexo;
	private String dtNascimento;
	private Integer idade;
	private String cidade; 
}
