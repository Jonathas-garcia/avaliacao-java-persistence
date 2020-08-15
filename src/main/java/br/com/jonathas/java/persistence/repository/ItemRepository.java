package br.com.jonathas.java.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.jonathas.java.persistence.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
