package br.com.springboot.CidadeClienteApi.enums;

public enum ErrorMenssages {

	CIDADE_NAO_ENCONTRADA("Cidade não encontrada!"),
	CIDADE_JA_EXISTE("Cidade já existe!"),
	CLIENTE_NAO_ENCONTRADO("Cliente não encontrado!");
	
	private String descricao;

	private ErrorMenssages(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
