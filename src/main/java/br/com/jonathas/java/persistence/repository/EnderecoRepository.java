package br.com.jonathas.java.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.jonathas.java.persistence.model.Endereco;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

}
