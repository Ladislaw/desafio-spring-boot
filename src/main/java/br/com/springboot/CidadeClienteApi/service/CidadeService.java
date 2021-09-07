package br.com.springboot.CidadeClienteApi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.springboot.CidadeClienteApi.dto.CidadeDTO;
import br.com.springboot.CidadeClienteApi.model.Cidade;
import br.com.springboot.CidadeClienteApi.model.Estado;
import br.com.springboot.CidadeClienteApi.repository.ICidadeRepository;
import br.com.springboot.CidadeClienteApi.repository.IEstadoRepository;

@Service
public class CidadeService {

	@Autowired
	private ICidadeRepository repo;

	@Autowired
	private IEstadoRepository estadoRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	public ResponseEntity<CidadeDTO> save(CidadeDTO dto){
		Cidade cidade = toEntity(dto);
		Estado estado = estadoRepo.findByNome(dto.getEstado()).stream().findAny().map(achou -> achou)
				.orElse(estadoRepo.save(cidade.getEstado()));
		
		cidade.setEstado(estado);
		cidade = repo.save(cidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(toDto(cidade));
	}
	
	public ResponseEntity<List<CidadeDTO>> getCidadesByNome(String search){
		return ResponseEntity.ok(repo.findByNome(search).stream().map(this::toDto).collect(Collectors.toList()));
	}

	public ResponseEntity<List<CidadeDTO>> getCidadesByEstado(String search){
		return ResponseEntity.ok(repo.findByEstado(search).stream().map(this::toDto).collect(Collectors.toList()));
	}
	
	private Cidade toEntity(CidadeDTO dto) {
		return mapper.map(dto, Cidade.class);
	}

	private CidadeDTO toDto(Cidade entity) {
		return mapper.map(entity, CidadeDTO.class);
	}
}
