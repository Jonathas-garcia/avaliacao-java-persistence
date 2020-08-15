package br.com.jonathas.java.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import br.com.jonathas.java.persistence.model.Pedido;
import br.com.jonathas.java.persistence.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido getPedidoById(Long id) {
		Pedido p = repository.findById(id).get();
		return p;
	}

	@Cacheable(value = "allPedidosCache", unless = "#result.size() == 0")
	public List<Pedido> getAllPedidos() {
		List<Pedido> lista = new ArrayList<>();
		repository.findAll().forEach(p -> lista.add(p));
		return lista;
	}

	@Caching(put = { @CachePut(value = "pedidoCache", key = "#pedido.id") }, evict = {
			@CacheEvict(value = "allPedidosCache", allEntries = true) })
	public Pedido savePedido(Pedido pedido) {
		pedido.getCliente().getEnderecos().stream().forEach(e -> e.setCliente(pedido.getCliente()));
		pedido.calculaPrecoTotal();
		pedido.getItens().stream().forEach(i -> i.setPedido(pedido));
		return repository.save(pedido);
	}

	@Caching(put = { @CachePut(value = "pedidoCache", key = "#pedido.id") }, evict = {
			@CacheEvict(value = "allPedidosCache", allEntries = true) })
	public Pedido updatePedido(Pedido p) {
		p.calculaPrecoTotal();
		Pedido pedido = repository.findById(p.getId()).get();
		p.setCliente(pedido.getCliente());
		return repository.save(p);
	}

	@CacheEvict(value = "allPedidosCache", allEntries = true)
	public void deletePedido(Long id) {
		repository.delete(repository.findById(id).get());
	}

}
