package br.com.springboot.CidadeClienteApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.CidadeClienteApi.dto.ClienteDTO;
import br.com.springboot.CidadeClienteApi.service.ClienteService;

@RestController
@RequestMapping(value = "/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {
	
	@Autowired
	private ClienteService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestBody ClienteDTO cliente){
		return service.save(cliente);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody ClienteDTO cliente){
		return service.save(cliente);
	}
	@GetMapping("/ById/{search}")
	public ResponseEntity<Object> getById(@PathVariable("search") Long search){
		return service.findById(search);
	}
	
	@GetMapping("/ByNome/{search}")
	public ResponseEntity<Object> getByNome(@PathVariable("search") String search){
		return service.findByNome(search);
	}
}
