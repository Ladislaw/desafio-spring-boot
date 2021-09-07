package br.com.springboot.CidadeClienteApi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.springboot.CidadeClienteApi.dto.CidadeDTO;
import br.com.springboot.CidadeClienteApi.model.Cidade;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapperFactory() {
		ModelMapper mapper = new ModelMapper();

		mapper.createTypeMap(CidadeDTO.class, Cidade.class).<String>addMapping(src -> src.getEstado(),
				(dest, value) -> dest.getEstado().setNome(value));

		mapper.createTypeMap(Cidade.class, CidadeDTO.class).<String>addMapping(src -> src.getEstado().getNome(),
				(dest, value) -> dest.setEstado(value));
		
		return mapper;
	}
}
