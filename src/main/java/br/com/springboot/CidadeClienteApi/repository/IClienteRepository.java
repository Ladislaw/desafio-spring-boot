package br.com.springboot.CidadeClienteApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.CidadeClienteApi.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long>{

}
