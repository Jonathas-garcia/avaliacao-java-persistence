package br.com.jonathas.java.persistence.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import br.com.jonathas.java.persistence.model.Cliente;
import br.com.jonathas.java.persistence.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente getClienteById(Long id) {
		Optional<Cliente> c = repository.findById(id);
		return c.get();
	}

	@Cacheable(value = "allClientesCache", unless = "#result.size() == 0")
	public List<Cliente> getAllClientes() {
		List<Cliente> lista = new ArrayList<>();
		repository.findAll().forEach(c -> lista.add(c));
		return lista;
	}

	@Caching(put = { @CachePut(value = "clienteCache", key = "#cliente.id") }, evict = {
			@CacheEvict(value = "allClientesCache", allEntries = true) })
	public Cliente saveCliente(Cliente cliente) {
		cliente.getEnderecos().stream().forEach(endereco -> endereco.setCliente(cliente));
		return repository.save(cliente);
	}

	@Caching(put = { @CachePut(value = "clienteCache", key = "#cliente.id") }, evict = {
			@CacheEvict(value = "allClientesCache", allEntries = true) })
	public Cliente updateCliente(Cliente cliente) {
		cliente.getEnderecos().stream().forEach(endereco -> endereco.setCliente(cliente));
		cliente.getPedidos().stream().forEach(pedido -> pedido.setCliente(cliente));
		return repository.save(cliente);
	}

	@CacheEvict(value = "allClientesCache", allEntries = true)
	public void deleteCliente(Long id) {
		repository.delete(repository.findById(id).get());
	}

}
