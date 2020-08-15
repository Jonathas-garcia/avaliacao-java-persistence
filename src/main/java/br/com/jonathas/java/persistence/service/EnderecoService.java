package br.com.jonathas.java.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import br.com.jonathas.java.persistence.model.Endereco;
import br.com.jonathas.java.persistence.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	public Endereco getEnderecoById(Long id) {
		Endereco e = repository.findById(id).get();
		return e;
	}

	@Cacheable(value = "allEnderecosCache", unless = "#result.size() == 0")
	public List<Endereco> getAllEnderecos() {
		List<Endereco> lista = new ArrayList<>();
		repository.findAll().forEach(e -> lista.add(e));
		return lista;
	}

	@Caching(put = { @CachePut(value = "enderecoCache", key = "#endereco.id") }, evict = {
			@CacheEvict(value = "allEnderecosCache", allEntries = true) })
	public Endereco saveEndereco(Endereco endereco) {
		return repository.save(endereco);
	}

	@Caching(put = { @CachePut(value = "enderecoCache", key = "#endereco.id") }, evict = {
			@CacheEvict(value = "allEnderecosCache", allEntries = true) })
	public Endereco updateEndereco(Endereco e) {
		Endereco end = repository.findById(e.getId()).get();
		e.setCliente(end.getCliente());

		return repository.save(e);
	}

	@CacheEvict(value = "allEnderecosCache", allEntries = true)
	public void deleteEndereco(Long id) {
		repository.delete(repository.findById(id).get());
	}

}
