package br.com.springboot.CidadeClienteApi.service;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.springboot.CidadeClienteApi.dto.CidadeDTO;
import br.com.springboot.CidadeClienteApi.enums.ErrorMenssages;
import br.com.springboot.CidadeClienteApi.model.Cidade;
import br.com.springboot.CidadeClienteApi.model.Estado;
import br.com.springboot.CidadeClienteApi.repository.ICidadeRepository;
import br.com.springboot.CidadeClienteApi.repository.IEstadoRepository;
import br.com.springboot.CidadeClienteApi.util.ApiError;

@Service
public class CidadeService {

	private ICidadeRepository repo;
	private IEstadoRepository estadoRepo;
	private ModelMapper mapper;
	
	public CidadeService(ICidadeRepository repo, IEstadoRepository estadoRepo, ModelMapper mapper) {
		this.repo = repo;
		this.estadoRepo = estadoRepo;
		this.mapper = mapper;
	}

	public ResponseEntity<Object> save(CidadeDTO dto){
		
		/* Verifica se a cidade a ser inserida já existe. */
		if (!repo.findByNome(dto.getNome()).isEmpty()) {
			return ApiError.response(HttpStatus.BAD_REQUEST, ErrorMenssages.CIDADE_JA_EXISTE.getDescricao());
		}
		
		/* Verifica se já existe o estado qual a cidade está sendo inserida e caso não exista é inserido antes da cidade. */
		Cidade cidade = mapToEntity(dto);
		Estado estado = estadoRepo.findByNome(dto.getEstado()).stream().findAny().map(achou -> achou)
				.orElse(estadoRepo.save(cidade.getEstado()));
		
		cidade.setEstado(estado);
		cidade = repo.save(cidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(mapToDto(cidade));
	}
	
	public ResponseEntity<Object> getCidadesByNome(String search){
		return ResponseEntity.ok(repo.findByNome(search).stream().map(this::mapToDto).collect(Collectors.toList()));
	}

	public ResponseEntity<Object> getCidadesByEstado(String search){
		return ResponseEntity.ok(repo.findByEstado(search).stream().map(this::mapToDto).collect(Collectors.toList()));
	}
	
	/* Converte da classe Dto para Entity */
	private Cidade mapToEntity(CidadeDTO dto) {
		return mapper.map(dto, Cidade.class);
	}

	/* Converte da classe Entity para Dto */
	private CidadeDTO mapToDto(Cidade entity) {
		return mapper.map(entity, CidadeDTO.class);
	}
}
