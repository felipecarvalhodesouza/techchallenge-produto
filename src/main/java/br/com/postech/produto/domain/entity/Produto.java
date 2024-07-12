package br.com.postech.produto.domain.entity;

import br.com.postech.produto.domain.enumeration.TipoProduto;

public class Produto {

	private Long id;
	private String nomeProduto;
	private TipoProduto tipoProduto;
	private double valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
