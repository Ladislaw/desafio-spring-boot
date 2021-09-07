package br.com.springboot.CidadeClienteApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.CidadeClienteApi.model.Estado;

@Repository
public interface IEstadoRepository extends JpaRepository<Estado, Long>{

	public List<Estado> findByNome(String nome);
}
