package br.com.springboot.CidadeClienteApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.springboot.CidadeClienteApi.model.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long>{

	@Query("SELECT c FROM Cliente c WHERE c.nome = :nome OR c.sobrenome = :nome")
	public List<Cliente> findByNomeOrSobrenome(@Param("nome") String nome);
}
