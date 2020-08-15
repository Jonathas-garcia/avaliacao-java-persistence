package br.com.jonathas.java.persistence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jonathas.java.persistence.model.Cliente;
import br.com.jonathas.java.persistence.service.ClienteService;

@RestController
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping("cliente/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id) {
		return new ResponseEntity<Cliente>(service.getClienteById(id), HttpStatus.OK);
	}

	@GetMapping("cliente")
	public ResponseEntity<List<Cliente>> getAllClientes() {
		return new ResponseEntity<List<Cliente>>(service.getAllClientes(), HttpStatus.OK);
	}

	@PutMapping("cliente")
	public ResponseEntity<Void> updateCliente(@RequestBody Cliente cliente, UriComponentsBuilder builder) {
		service.updateCliente(cliente);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PostMapping("cliente")
	public ResponseEntity<Void> addCliente(@RequestBody Cliente cliente, UriComponentsBuilder builder) {
		Cliente savedCliente = service.saveCliente(cliente);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/cliente/{id}").buildAndExpand(savedCliente.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@DeleteMapping("cliente/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable("id") Long id) {
		service.deleteCliente(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
