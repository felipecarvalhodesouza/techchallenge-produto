package br.com.postech.produto.infraestrutura.persistence;

import br.com.postech.produto.domain.enumeration.TipoProduto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity(name = "produto")
@Table(indexes = {@Index(name = "idx_ds_nome_produto", columnList="ds_nome_produto", unique = true)})
public class ProdutoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ds_nome_produto")
	private String nomeProduto;

	@Column(name = "ds_tipo_produto")
	@Enumerated(EnumType.STRING)
	private TipoProduto tipoProduto;

	@Column(name = "nm_valor")
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
