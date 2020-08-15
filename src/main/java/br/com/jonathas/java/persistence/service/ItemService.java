package br.com.jonathas.java.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import br.com.jonathas.java.persistence.model.Item;
import br.com.jonathas.java.persistence.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repository;

	public Item getItemById(Long id) {
		Item i = repository.findById(id).get();
		return i;
	}

	@Cacheable(value = "allItensCache", unless = "#result.size() == 0")
	public List<Item> getAllItens() {
		List<Item> lista = new ArrayList<>();
		repository.findAll().forEach(i -> lista.add(i));
		return lista;
	}

	@Caching(put = { @CachePut(value = "itemCache", key = "#item.id") }, evict = {
			@CacheEvict(value = "allItensCache", allEntries = true) })
	public Item saveItem(Item item) {
		return repository.save(item);
	}

	@Caching(put = { @CachePut(value = "itemCache", key = "#item.id") }, evict = {
			@CacheEvict(value = "allItenscache", allEntries = true) })
	public Item updateItem(Item i) {
		return repository.save(i);
	}

	@CacheEvict(value = "allItensCache", allEntries = true)
	public void deleteItem(Long id) {
		repository.delete(repository.findById(id).get());
	}
}
