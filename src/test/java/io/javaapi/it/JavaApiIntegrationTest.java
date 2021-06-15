package io.javaapi.it;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clasee que implementa teste integrado a API.
 * 
 * @author Gustavo
 *
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
public class JavaApiIntegrationTest {
	
	@Autowired
    private MockMvc mockMvc;

	@Test
	@Order(1)
	public void contextLoad() {
		assertNotNull(mockMvc);
	}
	
	@Test
	@Order(2)
	public void retornaCreateProduto() throws Exception {
		JSONObject jsonObj = setValuestoCreate();
		this.mockMvc.perform(post("/java-api/produtos").contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content(new ObjectMapper().writeValueAsString(jsonObj))).andExpect(status().isCreated());
	}
	
	@Test
	@Order(3)
	public void retornaUpdateProduto() throws Exception {
		JSONObject jsonObj = setValuesToUpdate();
		this.mockMvc.perform(put("/java-api/produtos/1").contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content(new ObjectMapper().writeValueAsString(jsonObj))).andExpect(status().isOk());
	}

	@Test
	@Order(4)
    public void retornaTodosProdutos() throws Exception {
		this.mockMvc.perform(get("/java-api/produtos")).andExpect(status().isOk());
    }
	
	@Test
	@Order(5)
    public void retornaRemoveAllProdutos() throws Exception {
		this.mockMvc.perform(delete("/java-api/produtos")).andExpect(status().isNoContent());
    }
	
	@SuppressWarnings("unchecked")
	private JSONObject setValuestoCreate() {
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", 1);
		jsonObj.put("nome", "Produto 1");
		jsonObj.put("valor", "39.90");
		
		return jsonObj;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject setValuesToUpdate() {
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", 1);
		jsonObj.put("nome", "Produto 1");
		jsonObj.put("valor", "40.99");
		
		return jsonObj;
	}

}