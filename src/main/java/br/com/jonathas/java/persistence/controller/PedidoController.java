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

import br.com.jonathas.java.persistence.model.Pedido;
import br.com.jonathas.java.persistence.service.PedidoService;

@RestController
public class PedidoController {

	@Autowired
	private PedidoService service;

	@GetMapping("pedido/{id}")
	public ResponseEntity<Pedido> getPedidoById(@PathVariable("id") Long id) {
		return new ResponseEntity<Pedido>(service.getPedidoById(id), HttpStatus.OK);
	}

	@GetMapping("pedido")
	public ResponseEntity<List<Pedido>> getAllPedidos() {
		return new ResponseEntity<List<Pedido>>(service.getAllPedidos(), HttpStatus.OK);
	}

	@PutMapping("pedido")
	public ResponseEntity<Void> updatePedido(@RequestBody Pedido pedido, UriComponentsBuilder builder) {
		service.updatePedido(pedido);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/pedido/{id}").buildAndExpand(pedido.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PostMapping("pedido")
	public ResponseEntity<Void> addPedido(@RequestBody Pedido pedido, UriComponentsBuilder builder) {
		Pedido savedPedido = service.savePedido(pedido);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/pedido/{id}").buildAndExpand(savedPedido.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@DeleteMapping("pedido/{id}")
	public ResponseEntity<Void> deletePedido(@PathVariable("id") Long id) {
		service.deletePedido(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
