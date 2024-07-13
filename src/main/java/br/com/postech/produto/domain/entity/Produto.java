package br.com.postech.produto.domain.entity;

import java.util.UUID;

import br.com.postech.produto.domain.enumeration.TipoProduto;

public class Produto {

	private UUID id;
	private String nomeProduto;
	private TipoProduto tipoProduto;
	private double valor;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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
