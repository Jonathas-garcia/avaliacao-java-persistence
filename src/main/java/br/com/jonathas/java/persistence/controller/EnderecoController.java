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

import br.com.jonathas.java.persistence.model.Endereco;
import br.com.jonathas.java.persistence.service.EnderecoService;

@RestController
public class EnderecoController {

	@Autowired
	private EnderecoService service;

	@GetMapping("/endereco")
	public ResponseEntity<List<Endereco>> getAllEnderecos() {
		return new ResponseEntity<List<Endereco>>(service.getAllEnderecos(), HttpStatus.OK);
	}

	@GetMapping("endereco/{id}")
	public ResponseEntity<Endereco> getEnderecoById(@PathVariable("id") Long id) {
		return new ResponseEntity<Endereco>(service.getEnderecoById(id), HttpStatus.OK);
	}

	@PostMapping("endereco")
	public ResponseEntity<Void> addEndereco(@RequestBody Endereco endereco, UriComponentsBuilder builder) {
		Endereco savedEndereco = service.saveEndereco(endereco);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/endereco/{id}").buildAndExpand(savedEndereco.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("endereco")
	public ResponseEntity<Void> updateEndereco(@RequestBody Endereco endereco, UriComponentsBuilder builder) {
		service.updateEndereco(endereco);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/endereco/{id}").buildAndExpand(endereco.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@DeleteMapping("endereco/{id}")
	public ResponseEntity<Void> deleteEndereco(@PathVariable("id") Long id) {
		service.deleteEndereco(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
