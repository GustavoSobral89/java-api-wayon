package io.javaapi.service;

import java.util.List;

import io.javaapi.model.Categoria;

/**
 * Definicao das regras de negocio para manipular oo objeto {@link Categoria}.
 * 
 * 
 * @author Gustavo
 */
public interface CategoriaService {


	/**
	 * Método para criar a lista de categorias.
	 * 
	 * @author Gustavo
	 */
	void createCategoriaList();
	

	/**
	 * Método para adicionar um objeto Categoria.
	 * 
	 * @author Gustavo
	 * 
	 * @param categoria
	 */
	Categoria add(Categoria categoria);

	/**
	 * Método para buscar todas as categorias.
	 * 
	 * @author Gustavo
	 * 
	 * @return List
	 */
	List<Categoria> find();

	/**
	 * Método para buscar a Categoria por Nome.
	 * 
	 * @author Gustavo
	 * 
	 * @param id
	 * @return Categoria
	 */
	Categoria findByNome(String nome);

	/**
	 * Método para deletar as categorias.
	 * 
	 * @author Gustavo
	 */
	void delete(Categoria categoria);
}
