package br.com.springboot.CidadeClienteApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.springboot.CidadeClienteApi.model.Cidade;

@Repository
public interface ICidadeRepository extends JpaRepository<Cidade, Long> {
	
	public List<Cidade> findByNome(String nome);
	
	@Query("SELECT c FROM Cidade c WHERE c.estado.nome = :estado")
	public List<Cidade> findByEstado(@Param("estado") String estado);
}
