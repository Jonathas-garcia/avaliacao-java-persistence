package br.com.jonathas.java.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.jonathas.java.persistence.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
	Optional<Cliente> findByNome(String nome);

	Optional<Cliente> findById(Long id);

}
