package br.com.springboot.CidadeClienteApi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

	private Long id;
	private String nome;
	private String sobrenome;
	private String sexo;
	private String dtNascimento;
	private Integer idade;
	private String cidade;
	private String estado;
}
