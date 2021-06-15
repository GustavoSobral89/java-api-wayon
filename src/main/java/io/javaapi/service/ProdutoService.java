package io.javaapi.service;

import java.util.List;

import org.json.simple.JSONObject;

import io.javaapi.model.Produto;

/**
 * Definicao das regras de negocio para manipular oo objeto {@link Produto}.
 * 
 * 
 * @author Gustavo
 */
public interface ProdutoService {


	/**
	 * Método para criar a lista de produtos.
	 * 
	 * @author Gustavo
	 */
	void createProdutoList();

	/**
	 * Método para verificar o formato do JSON.
	 * 
	 * @author Gustavo
	 * 
	 * @param jsonInString
	 * @return boolean
	 */
	boolean isJSONValid(String jsonInString);

	/**
	 * Método para criar o produto.
	 * 
	 * @author Gustavo
	 * 
	 * @param jsonObj
	 * @return Produto
	 */
	Produto create(JSONObject jsonObj);

	/**
	 * Método para atualizar o produto.
	 * 
	 * @author Gustavo
	 * 
	 * @param produto
	 * @param jsonObj
	 * 
	 * @return Produto
	 */
	Produto update(Produto produto, JSONObject jsonObj);

	/**
	 * Método para adicionar um objeto Produto.
	 * 
	 * @author Gustavo
	 * 
	 * @param produto
	 */
	void add(Produto produto);

	/**
	 * Método para buscar todos os produtos.
	 * 
	 * @author Gustavo
	 * 
	 * @return List
	 */
	List<Produto> find();

	/**
	 * Método para buscar o produto por ID.
	 * 
	 * @author Gustavo
	 * 
	 * @param id
	 * @return Produto
	 */
	Produto findById(long id);

	/**
	 * Método para deletar os produtos.
	 * 
	 * @author Gustavo
	 */
	void delete();

	/**
	 * Método para limpar os objetos.
	 * 
	 * @author Gustavo
	 */
	void clearObjects();
}
