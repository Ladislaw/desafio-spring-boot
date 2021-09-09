package br.com.springboot.CidadeClienteApi.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.CidadeClienteApi.dto.ClienteDTO;
import br.com.springboot.CidadeClienteApi.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("API Rest Cliente")
@RestController
@RequestMapping(value = "/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {
	
	private ClienteService service;
	
	public ClienteController(ClienteService service) {
		this.service = service;
	}

	@ApiOperation("Cria um novo cliente")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestBody ClienteDTO cliente){
		return service.save(cliente);
	}

	@ApiOperation("Altera dados de um cliente existente")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody ClienteDTO cliente){
		return service.save(cliente);
	}
	
	@ApiOperation("Deleta um cliente existente")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id){
		return service.delete(id);
	}
	
	@ApiOperation("Buscar Cliente pelo ID")
	@GetMapping("/ById/{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") Long id){
		return service.findById(id);
	}
	
	@ApiOperation("Buscar Cliente pelo Nome")
	@GetMapping("/ByNome/{nome}")
	public ResponseEntity<Object> getByNome(@PathVariable("nome") String nome){
		return service.findByNome(nome);
	}
}
