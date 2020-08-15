package br.com.jonathas.java.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.jonathas.java.persistence.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

}
