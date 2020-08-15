package br.com.jonathas.java.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.jonathas.java.persistence.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}
