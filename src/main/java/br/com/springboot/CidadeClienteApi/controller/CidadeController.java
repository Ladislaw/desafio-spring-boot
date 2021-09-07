package br.com.springboot.CidadeClienteApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(value = "/cidade", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CidadeController {

	@Autowired
	private CidadeService service;
	
	@PostMapping(value = "/salvar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CidadeDTO> saveCidade(@RequestBody CidadeDTO dto){
		return service.save(dto);
	}

	@GetMapping("/find/{nome}")
	public ResponseEntity<List<CidadeDTO>> findByNome(@PathVariable("nome") String nome){
		return service.getCidadesByNome(nome);
	}

	@GetMapping("/estado/find/{nome}")
	public ResponseEntity<List<CidadeDTO>> findByEstadoNome(@PathVariable("nome") String nome){
		return service.getCidadesByEstado(nome);
	}
}
