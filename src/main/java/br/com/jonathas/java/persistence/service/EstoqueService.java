package br.com.jonathas.java.persistence.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jonathas.java.persistence.model.Estoque;
import br.com.jonathas.java.persistence.repository.EstoqueRepository;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepository repository;

	public Estoque getEstoqueById(Long id) {
		Optional<Estoque> c = repository.findById(id);
		return c.get();
	}

	public List<Estoque> getAllEstoques() {
		List<Estoque> lista = new ArrayList<>();
		repository.findAll().forEach(e -> lista.add(e));
		return lista;
	}

	public Estoque saveEstoque(Estoque e) {
		return repository.save(e);
	}

	public Estoque updateEstoque(Estoque e) {
		return repository.save(e);
	}

	public void deleteEstoque(Long id) {
		repository.delete(repository.findById(id).get());
	}
}
