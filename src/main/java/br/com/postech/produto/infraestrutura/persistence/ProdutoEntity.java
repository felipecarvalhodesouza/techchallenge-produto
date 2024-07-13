package br.com.postech.produto.infraestrutura.persistence;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.postech.produto.domain.enumeration.TipoProduto;
import jakarta.persistence.Id;

@Document(collection = "produtos")
public class ProdutoEntity{

	@Id
	private String id;
	private String nomeProduto;
	private TipoProduto tipoProduto;
	private double valor;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double preco) {
		this.valor = preco;
	}

}
