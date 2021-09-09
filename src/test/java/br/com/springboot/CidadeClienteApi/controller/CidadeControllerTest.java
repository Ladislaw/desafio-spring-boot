package br.com.springboot.CidadeClienteApi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.springboot.CidadeClienteApi.dto.CidadeDTO;
import br.com.springboot.CidadeClienteApi.service.CidadeService;

@ExtendWith(MockitoExtension.class)
public class CidadeControllerTest {

	@Mock
	private CidadeService service;
	
	@InjectMocks
    private CidadeController controller;
	
	@Test
	public void createCidade_test() throws Exception {
		CidadeDTO dto = new CidadeDTO("Salvador", "Bahia");
		ResponseEntity<Object> responseTest = ResponseEntity.status(HttpStatus.CREATED).body(dto);
		
		Mockito.when(service.save(dto)).thenReturn(responseTest);
		
		assertEquals(HttpStatus.CREATED.value(), controller.saveCidade(dto).getStatusCodeValue());
	}
	
	@Test
	public void findByNome_test() {
		CidadeDTO dto = new CidadeDTO("Salvador", "Bahia");
		List<CidadeDTO> cidades = Arrays.asList(dto);
		ResponseEntity<Object> responseTest = ResponseEntity.status(HttpStatus.OK).body(cidades);
		
		Mockito.when(service.getCidadesByNome(dto.getNome())).thenReturn(responseTest);
		
		assertEquals(HttpStatus.OK.value(), controller.findByNome(dto.getNome()).getStatusCodeValue());
	}

	@Test
	public void findByEstadoNome_test() {
		CidadeDTO dto = new CidadeDTO("Salvador", "Bahia");
		List<CidadeDTO> cidades = Arrays.asList(dto);
		ResponseEntity<Object> responseTest = ResponseEntity.status(HttpStatus.OK).body(cidades);
		
		Mockito.when(service.getCidadesByEstado(dto.getEstado())).thenReturn(responseTest);
		
		assertEquals(HttpStatus.OK.value(), controller.findByEstadoNome(dto.getEstado()).getStatusCodeValue());
	}
}
