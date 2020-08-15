package br.com.jonathas.java.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.jonathas.java.persistence.model.Estoque;

public interface EstoqueRepository extends CrudRepository<Estoque, Long> {

}
