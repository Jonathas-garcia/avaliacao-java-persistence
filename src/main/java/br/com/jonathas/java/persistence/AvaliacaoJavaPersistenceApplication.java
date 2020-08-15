package br.com.jonathas.java.persistence;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.jonathas.java.persistence.model.Cliente;
import br.com.jonathas.java.persistence.model.Endereco;
import br.com.jonathas.java.persistence.model.Item;
import br.com.jonathas.java.persistence.model.Pedido;
import br.com.jonathas.java.persistence.model.Produto;
import br.com.jonathas.java.persistence.service.PedidoService;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class AvaliacaoJavaPersistenceApplication implements CommandLineRunner {

	@Autowired
	private PedidoService pedidoService;

	public static void main(String[] args) {
		SpringApplication.run(AvaliacaoJavaPersistenceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Start!");
		massaTeste();
	}

	private void massaTeste() {
		Pedido pedido = new Pedido();
		Cliente cliente = new Cliente();
		Endereco end = new Endereco();
		Produto produto = new Produto();
		Produto produto2 = new Produto();

		Set<Endereco> listEnd = new HashSet<>();
		listEnd.add(end);

		Set<Item> itens = new HashSet<>();
		Item item = new Item();
		Item item2 = new Item();
		itens.add(item);
		itens.add(item2);

		item.setPedido(pedido);
		item.setProduto(produto);
		item.setQuantidade(1);
		
		item2.setPedido(pedido);
		item2.setProduto(produto2);
		item2.setQuantidade(1);

		produto.setItens(itens);
		produto.setNome("Monitor Benq");
		produto.setValor(new BigDecimal(1500));
		
		produto2.setItens(itens);
		produto2.setNome("Monitor Acer");
		produto2.setValor(new BigDecimal(1000));

		end.setCep("03687000");
		end.setCidade("São Paulo");
		end.setRua("Avenida Paulista");
		end.setCliente(cliente);

		cliente.setNome("João");
		cliente.setEnderecos(listEnd);

		pedido.setCliente(cliente);
		pedido.setItens(itens);

		pedidoService.savePedido(pedido);

	}

}
