package br.com.springboot.CidadeClienteApi.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.springboot.CidadeClienteApi.dto.CidadeDTO;
import br.com.springboot.CidadeClienteApi.dto.ClienteDTO;
import br.com.springboot.CidadeClienteApi.model.Cidade;
import br.com.springboot.CidadeClienteApi.model.Cliente;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapperFactory() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		/* Conversão de data string para localdate */
	    Converter<String, LocalDate> toLocalDate = new AbstractConverter<String, LocalDate>() {
	        @Override
	        protected LocalDate convert(String source) {
	            return LocalDate.parse(source, formatter);
	        }
	    };

	    /* Conversão de data localdate para string */
	    Converter<LocalDate, String> toStringDate = new AbstractConverter<LocalDate, String>() {
	    	@Override
	    	protected String convert(LocalDate source) {
	    		return source.format(formatter);
	    	}
	    };
	    
	    /* Conversão de sexo do cliente para descrição */
	    Converter<String, String> toDescricaoSexo = new AbstractConverter<String, String>() {
	    	@Override
	    	protected String convert(String source) {
	    		switch (source) {
				case "M":
					return "Masculino";
				case "F":
					return "Feminino";
				default:
					return null;
				}
	    	}
	    };

	    /* Definição de conversões */
	    ModelMapper mapper = new ModelMapper();

	    /* Classe DTO para Entidade */
		mapper.createTypeMap(CidadeDTO.class, Cidade.class).<String>addMapping(src -> src.getEstado(),
				(dest, value) -> dest.getEstado().setNome(value));

		mapper.createTypeMap(ClienteDTO.class, Cliente.class)
				.addMappings(m -> m.using(toLocalDate).map(ClienteDTO::getDtNascimento, Cliente::setDtNascimento));
		
		/* Classe Entidade para DTO */
		mapper.createTypeMap(Cidade.class, CidadeDTO.class).<String>addMapping(src -> src.getEstado().getNome(),
				(dest, value) -> dest.setEstado(value));
		
		mapper.createTypeMap(Cliente.class, ClienteDTO.class)
			.<String>addMapping(src -> src.getCidade().getNome(), (dest, value) -> dest.setCidade(value))
			.<String>addMapping(src -> src.getCidade().getEstado().getNome(), (dest, value) -> dest.setEstado(value))
			.addMappings(m -> m.using(toDescricaoSexo).map(Cliente::getSexo, ClienteDTO::setSexo))
			.addMappings(m -> m.using(toStringDate).map(Cliente::getDtNascimento, ClienteDTO::setDtNascimento));
		
		return mapper;
	}
}
