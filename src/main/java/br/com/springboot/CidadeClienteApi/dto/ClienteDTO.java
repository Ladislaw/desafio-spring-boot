package br.com.springboot.CidadeClienteApi.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Cliente")
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
