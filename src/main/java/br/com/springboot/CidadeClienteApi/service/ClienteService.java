package br.com.springboot.CidadeClienteApi.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.springboot.CidadeClienteApi.dto.ClienteDTO;
import br.com.springboot.CidadeClienteApi.enums.ErrorMenssages;
import br.com.springboot.CidadeClienteApi.model.Cidade;
import br.com.springboot.CidadeClienteApi.model.Cliente;
import br.com.springboot.CidadeClienteApi.repository.ICidadeRepository;
import br.com.springboot.CidadeClienteApi.repository.IClienteRepository;
import br.com.springboot.CidadeClienteApi.util.ApiError;

@Service
public class ClienteService {
	
	private IClienteRepository repo;
	private ICidadeRepository cidadeRepo;
	private ModelMapper mapper;
	
	public ClienteService(IClienteRepository repo, ICidadeRepository cidadeRepo, ModelMapper mapper) {
		this.repo = repo;
		this.cidadeRepo = cidadeRepo;
		this.mapper = mapper;
	}

	public ResponseEntity<Object> save(ClienteDTO dto){
		
		HttpStatus httpStatusReturn = HttpStatus.OK;
		
		/* Verifica se existe a cidade em que se quer cadastrar o cliente. */
		Optional<Cidade> cidade = cidadeRepo.findByNome(dto.getCidade()).stream().findAny();
		if (!cidade.isPresent()) {
			return ApiError.response(HttpStatus.NOT_FOUND, ErrorMenssages.CIDADE_NAO_ENCONTRADA.getDescricao());
		}
		
		/* Caso seja um Update, verifica se realmente existe um cliente com id correspondente. */
		if (dto.getId() != null && !repo.existsById(dto.getId())) {
			return ApiError.response(HttpStatus.NOT_FOUND, ErrorMenssages.CLIENTE_NAO_ENCONTRADO.getDescricao());
		} else {
			httpStatusReturn = HttpStatus.CREATED;
		}
		
		Cliente cliente = mapToEntity(dto);
		cliente.setCidade(cidade.get());
		cliente = repo.save(cliente);
		
		return ResponseEntity.status(httpStatusReturn).body(mapToDto(cliente));
	}
	
	public ResponseEntity<Object> delete(Long id){
		/* Verifica se existe o cliente correspondente ao id a ser deletado. */
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ApiError.response(HttpStatus.NOT_FOUND, ErrorMenssages.CLIENTE_NAO_ENCONTRADO.getDescricao());
	}
	
	public ResponseEntity<Object> findById(Long id){
		/* Verifica se existe o cliente pelo id */
		Optional<Cliente> cliente = repo.findById(id);
		if (!cliente.isPresent()) {
			return ApiError.response(HttpStatus.NOT_FOUND,ErrorMenssages.CLIENTE_NAO_ENCONTRADO.getDescricao());
		}
		
		return ResponseEntity.ok(mapToDto(cliente.get()));
	}
	
	public ResponseEntity<Object> findByNome(String search){
		return ResponseEntity.ok(repo.findByNomeOrSobrenome(search).stream().map(this::mapToDto).collect(Collectors.toList()));
	}
	
	/* Converte da classe Dto para Entity */
	private Cliente mapToEntity(ClienteDTO dto) {
		return mapper.map(dto, Cliente.class);
	}

	/* Converte da classe Entity para Dto */
	private ClienteDTO mapToDto(Cliente entity) {
		return mapper.map(entity, ClienteDTO.class);
	}
}
