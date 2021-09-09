package br.com.springboot.CidadeClienteApi.service;

import java.util.Optional;
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
	
	public ResponseEntity<Object> save(ClienteDTO dto){
		
		HttpStatus httpStatusReturn = HttpStatus.OK;
		
		Optional<Cidade> cidade = cidadeRepo.findByNome(dto.getCidade()).stream().findAny();
		if (!cidade.isPresent()) {
			return ApiError.response(HttpStatus.NOT_FOUND, "Cidade não encontrada!");
		}
		
		if (dto.getId() != null && !repo.existsById(dto.getId())) {
			return ApiError.response(HttpStatus.NOT_FOUND, "Cliente não encontrado!");
		} else {
			httpStatusReturn = HttpStatus.CREATED;
		}
		
		Cliente cliente = mapToEntity(dto);
		cliente.setCidade(cidade.get());
		cliente = repo.save(cliente);
		
		return ResponseEntity.status(httpStatusReturn).body(mapToDto(cliente));
	}
	
	public ResponseEntity<Object> delete(Long id){
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return ResponseEntity.ok("Cliente apagado com Sucesso!");
		}
		return ApiError.response(HttpStatus.NOT_FOUND, "Cliente com id: " + id + " não encontrado!");
	}
	
	public ResponseEntity<Object> findById(Long id){
		
		Optional<Cliente> cliente = repo.findById(id);
		if (!cliente.isPresent()) {
			return ApiError.response(HttpStatus.NOT_FOUND, "Cliente não encontrada!");
		}
		
		return ResponseEntity.ok(mapToDto(cliente.get()));
	}
	
	public ResponseEntity<Object> findByNome(String search){
		return ResponseEntity.ok(repo.findByNomeOrSobrenome(search).stream().map(this::mapToDto).collect(Collectors.toList()));
	}
	
	private Cliente mapToEntity(ClienteDTO dto) {
		return mapper.map(dto, Cliente.class);
	}

	private ClienteDTO mapToDto(Cliente entity) {
		return mapper.map(entity, ClienteDTO.class);
	}
}
