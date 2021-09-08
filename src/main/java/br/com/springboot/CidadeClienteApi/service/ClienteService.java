package br.com.springboot.CidadeClienteApi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.springboot.CidadeClienteApi.dto.ClienteDTO;
import br.com.springboot.CidadeClienteApi.model.Cidade;
import br.com.springboot.CidadeClienteApi.model.Cliente;
import br.com.springboot.CidadeClienteApi.repository.ICidadeRepository;
import br.com.springboot.CidadeClienteApi.repository.IClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private IClienteRepository repo;

	@Autowired
	private ICidadeRepository cidadeRepo;

	@Autowired
	private ModelMapper mapper;
	
	public ResponseEntity<ClienteDTO> save(ClienteDTO dto){
		
		Cidade cidade = cidadeRepo.findByNome(dto.getCidade()).stream().findAny().map(achou -> achou)
				.orElseThrow(() -> new RuntimeException("Cidade não encontrada!"));
		
		Cliente cliente = toEntity(dto);
		cliente.setCidade(cidade);
		cliente = repo.save(cliente);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(toDto(cliente));
	}
	
	public ResponseEntity<List<ClienteDTO>> findByNome(String search){
		return ResponseEntity.ok(repo.findByNome(search).stream().map(this::toDto).collect(Collectors.toList()));
	}
	
	public ResponseEntity<ClienteDTO> findById(Long id){
		return ResponseEntity.ok(repo.findById(id).map(achou -> toDto(achou))
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado!")));
	}
	
	private Cliente toEntity(ClienteDTO dto) {
		return mapper.map(dto, Cliente.class);
	}

	private ClienteDTO toDto(Cliente entity) {
		return mapper.map(entity, ClienteDTO.class);
	}
}
