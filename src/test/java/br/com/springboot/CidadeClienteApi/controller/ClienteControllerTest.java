package br.com.springboot.CidadeClienteApi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.springboot.CidadeClienteApi.dto.ClienteDTO;
import br.com.springboot.CidadeClienteApi.service.ClienteService;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

	@Mock
	private ClienteService service;
	
	@InjectMocks
    private ClienteController controller;
	
	@Test
	public void createCliente_teste() {
		ClienteDTO dto = getDto();
		ResponseEntity<Object> responseTest = ResponseEntity.status(HttpStatus.CREATED).body(dto);
		
		Mockito.when(service.save(dto)).thenReturn(responseTest);
		
		assertEquals(HttpStatus.CREATED.value(), controller.create(dto).getStatusCodeValue());
	}
	
	@Test
	public void updateCliente_teste() {
		ClienteDTO dto = getDto();
		dto.setId(1l);
		dto.setNome("Fernando");
		
		ResponseEntity<Object> responseTest = ResponseEntity.status(HttpStatus.OK).body(dto);
		
		Mockito.when(service.save(dto)).thenReturn(responseTest);
		
		assertEquals(HttpStatus.OK.value(), controller.update(dto).getStatusCodeValue());
	}

	@Test
	public void deleteCliente_teste() {
		ClienteDTO dto = getDto();
		dto.setId(1l);
		
		ResponseEntity<Object> responseTest = ResponseEntity.status(HttpStatus.OK).body("");
		
		Mockito.when(service.delete(dto.getId())).thenReturn(responseTest);
		
		assertEquals(HttpStatus.OK.value(), controller.delete(dto.getId()).getStatusCodeValue());
	}
	
	@Test
	public void findById_test() {
		ClienteDTO dto = getDto();
		dto.setId(1l);
		
		ResponseEntity<Object> responseTest = ResponseEntity.status(HttpStatus.OK).body(dto);
		
		Mockito.when(service.findById(dto.getId())).thenReturn(responseTest);
		
		assertEquals(HttpStatus.OK.value(), controller.getById(dto.getId()).getStatusCodeValue());
	}

	@Test
	public void findByNome_test() {
		ClienteDTO dto = getDto();
		dto.setId(1l);
		
		ResponseEntity<Object> responseTest = ResponseEntity.status(HttpStatus.OK).body(dto);
		
		Mockito.when(service.findByNome(dto.getNome())).thenReturn(responseTest);
		
		assertEquals(HttpStatus.OK.value(), controller.getByNome(dto.getNome()).getStatusCodeValue());
	}
	
	private ClienteDTO getDto() {
		ClienteDTO retorno = new ClienteDTO();
		retorno.setNome("Jos??");
		retorno.setSobrenome("da Silva");
		retorno.setDtNascimento("14/04/1968");
		retorno.setIdade(53);
		retorno.setCidade("Salvador");
		retorno.setSexo("M");
		return retorno;
	}
}
