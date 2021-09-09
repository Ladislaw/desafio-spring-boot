package br.com.springboot.CidadeClienteApi.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.CidadeClienteApi.dto.CidadeDTO;
import br.com.springboot.CidadeClienteApi.service.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("API Rest Cidade")
@RestController
@RequestMapping(value = "/cidade", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CidadeController {

	private CidadeService service;
	
	public CidadeController(CidadeService service) {
		this.service = service;
	}
	
	@ApiOperation("Cria uma nova Cidade")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveCidade(@RequestBody CidadeDTO dto){
		return service.save(dto);
	}

	@ApiOperation("Busca cidades por Nome")
	@GetMapping("/ByNome/{search}")
	public ResponseEntity<Object> findByNome(@PathVariable("search") String search){
		return service.getCidadesByNome(search);
	}

	@ApiOperation("Busca Cidades pelo Estado")
	@GetMapping("/ByEstado/{search}")
	public ResponseEntity<Object> findByEstadoNome(@PathVariable("search") String search){
		return service.getCidadesByEstado(search);
	}
}
