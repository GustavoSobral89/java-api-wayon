package io.javaapi.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que implementa a estrutura Produto.
 * 
 * @author Gustavo
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
public class Produto implements Serializable{

	private static final long serialVersionUID = 8636949375642193473L;
	
	@ApiModelProperty(value = "ID do Produto.")
	private Long id;
	
	@ApiModelProperty(value = "Nome do Produto.")
	private String nome;
	
	@ApiModelProperty(value = "Valor do Produto.")
	private BigDecimal valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
