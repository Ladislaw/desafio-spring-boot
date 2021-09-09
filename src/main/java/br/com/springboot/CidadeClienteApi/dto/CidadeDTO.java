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
@ApiModel("Cidade")
public class CidadeDTO {

	private String nome;
	private String estado;
}
