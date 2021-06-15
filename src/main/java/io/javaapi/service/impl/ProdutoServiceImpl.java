package io.javaapi.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.javaapi.model.Produto;
import io.javaapi.service.ProdutoService;

/**
 * Implementacao das regras de negocio definidas em {@link ProdutoService}.
 * 
 * @author Gustavo
 * 
 */
@Service
public class ProdutoServiceImpl implements ProdutoService {

	private List<Produto> produtoList;

	/**
	 * @see ProdutoService#createProdutoList()
	 */
	public void createProdutoList() {
		if (produtoList == null) {
			produtoList = new ArrayList<>();
		}
	}

	/**
	 * @see ProdutoService#isJSONValid(jsonInString)
	 */
	public boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	private long parseId(JSONObject jsonObj) {
		return Long.valueOf((int) jsonObj.get("id"));
	}

	private BigDecimal parseValor(JSONObject jsonObj) {
		return new BigDecimal((String) jsonObj.get("valor"));
	}

	private void setValues(JSONObject jsonObj, Produto produto) {
		String nome = (String) jsonObj.get("nome");

		produto.setNome(nome);
		produto.setValor(parseValor(jsonObj));
	}

	/**
	 * @see ProdutoService#create(jsonObj)
	 */
	public Produto create(JSONObject jsonObj) {
		Produto produto = new Produto();
		produto.setId(parseId(jsonObj));
		setValues(jsonObj, produto);

		return produto;
	}

	/**
	 * @see ProdutoService#update(produto, jsonObj)
	 */
	public Produto update(Produto produto, JSONObject jsonObj) {

		setValues(jsonObj, produto);
		return produto;
	}

	/**
	 * @see ProdutoService#add(produto)
	 */
	public void add(Produto produto) {
		createProdutoList();
		produtoList.add(produto);
	}

	/**
	 * @see ProdutoService#find()
	 */
	public List<Produto> find() {
		createProdutoList();
		return produtoList;
	}

	/**
	 * @see ProdutoService#findById(id)
	 */
	public Produto findById(long id) {
		return produtoList.stream().filter(t -> id == t.getId()).collect(Collectors.toList()).get(0);
	}

	/**
	 * @see ProdutoService#delete()
	 */
	public void delete() {
		produtoList.clear();
	}

	/**
	 * @see ProdutoService#clearObjects()
	 */
	public void clearObjects() {
		produtoList = null;
	}
}
