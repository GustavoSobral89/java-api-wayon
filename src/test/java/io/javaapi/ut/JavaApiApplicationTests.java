package io.javaapi.ut;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.javaapi.model.Produto;
import io.javaapi.service.ProdutoService;

/**
 * Classe que implementa o teste unitário da API.
 * 
 * @author Gustavo
 *
 */
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class JavaApiApplicationTests {

	@Autowired
	private ProdutoService produtoService;

	@BeforeAll
	public void setUp() {
		produtoService.createProdutoList();
	}

	@Test
	@Order(1)
	public void returnaNotNullProdutoService() {
		assertNotNull(produtoService);
	}

	@Test
	@Order(2)
	@SuppressWarnings("unchecked")
	public void retornaProdutoCriadoComSucesso() throws Exception {

		JSONObject jsonObj = new JSONObject();

		jsonObj.put("id", 1);
		jsonObj.put("nome", "Produto 1");
		jsonObj.put("valor", "39.90");

		Produto produto = produtoService.create(jsonObj);

		assertNotNull(produto);
		assertEquals(produto.getId().intValue(), jsonObj.get("id"));
		assertEquals(produto.getNome(), jsonObj.get("nome"));
		assertEquals(produto.getValor().toString(), jsonObj.get("valor"));
	}

	@AfterAll
	public void tearDown() {
		produtoService.clearObjects();
	}
}
