package br.com.springboot.CidadeClienteApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.CidadeClienteApi.model.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long>{

	public List<Cliente> findByNome(String nome);
}
