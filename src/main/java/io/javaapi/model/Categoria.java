package io.javaapi.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que implementa a estrutura Categoria.
 * 
 * @author Gustavo
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria  implements Serializable{

	private static final long serialVersionUID = -1750231927786063326L;
	
	@ApiModelProperty(value = "ID/Nome da Categoria.")
	@Size(min = 3, max = 20)
	@NotNull
	private String nome;
	
	@JsonIgnore
	public boolean isCategoriaValid() {
		return(!this.nome.trim().equals("") && this.nome!=null);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}