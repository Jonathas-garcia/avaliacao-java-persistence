package br.com.jonathas.java.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;

	@OneToOne
	@JoinColumn(name = "produto_id")
	public Produto produto;

	public Integer quantidade;

	@Override
	public String toString() {
		return "Estoque (id: " + id + ", Produto: " + produto.getNome() + ", quantidade: " + quantidade;
	}

}
