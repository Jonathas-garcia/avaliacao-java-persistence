package br.com.jonathas.java.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import br.com.jonathas.java.persistence.model.Produto;
import br.com.jonathas.java.persistence.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public Produto getProdutoById(Long id) {
		Produto p = repository.findById(id).get();
		return p;
	}

	@Cacheable(value = "allProdutosCache", unless = "#result.size() == 0")
	public List<Produto> getAllProdutos() {
		List<Produto> lista = new ArrayList<>();
		repository.findAll().forEach(p -> lista.add(p));
		return lista;
	}

	@Caching(put = { @CachePut(value = "produtoCache", key = "#produto.id") }, evict = {
			@CacheEvict(value = "allProdutosCache", allEntries = true) })
	public Produto saveProduto(Produto produto) {
		return repository.save(produto);
	}

	@Caching(put = { @CachePut(value = "produtoCache", key = "#produto.id") }, evict = {
			@CacheEvict(value = "allProdutosCache", allEntries = true) })
	public Produto updateProduto(Produto p) {
		return repository.save(p);
	}

	@CacheEvict(value = "allProdutosCache", allEntries = true)
	public void deleteProduto(Long id) {
		repository.delete(repository.findById(id).get());
	}

}
