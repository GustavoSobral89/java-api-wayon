package io.javaapi.controller;

import java.net.URI;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.javaapi.model.Produto;
import io.javaapi.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 * SpringBoot RestController que cria todos os end points de serviço relacionados a produtos.
 * 
 * @author Gustavo
 *
 */
@RestController
@RequestMapping("/java-api/produtos")
public class ProdutoController {

	private static final Logger logger = Logger.getLogger(ProdutoController.class);

	@Autowired
	private ProdutoService produtoService;

	/**
	 * Método que lista todos os produtos.
	 * 
	 * @return ResponseEntity com o objeto <code>List<Produto></code> e o  HTTP status.
	 * 
	 * HTTP Status:
	 * 
	 * 200 - OK.
	 * 
	 */
	@ApiOperation(
			value = "Método que lista todos os produtos.",
			response = Produto.class,
			responseContainer = "List"
			)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "OK"),
//	    @ApiResponse(code = 404, message = "Not Found")
	})
	@GetMapping(produces = { "application/json" })
//	public ResponseEntity<List<Produto>> find() {
	public List<Produto> find() {
//		if (produtoService.find().isEmpty())
//			return ResponseEntity.notFound().build();
		
		logger.info(produtoService.find());
		return produtoService.find();
		//notFound é pra quando nao encontra uma URI
		//retorna ok mas com lista vazia, o correto
		//noContent não entra pois retorna algo: lista vazia
	}

	/**
	 * Método que deleta todos os produtos.
	 * 
	 * @author Gustavo
	 * 
	 * @return retorna os valores:
	 * 
	 * 204 - Delete com sucesso.
	 * 205 - Delete sem sucesso.
	 * 500 - Erro na API.
	 */
	@ApiOperation(value = "Método que deleta todos os produtos.")
	@ApiResponses(value = {
	    @ApiResponse(code = 204, message = "Delete com sucesso."),
	    @ApiResponse(code = 205, message = "Delete sem sucesso."),
	    @ApiResponse(code = 500, message = "Erro na API.")
	})
	@DeleteMapping
	public ResponseEntity<Boolean> delete() {
		try {
			produtoService.delete();
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			logger.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	/**
	 * Método que cria o produto.
	 * 
	 * @author Gustavo
	 * 
	 * @param jsonObj
	 * 
	 * @return ResponseEntity com o objeto <code>Produto</code> e o HTTP status
	 * 
	 * HTTP Status:
	 * 
	 * 201 - Criado com sucesso.
	 * 400 - Bad Request: JSON Inválido.
	 * 422 - JSON mal formatado.
	 * 500 - Erro na API.
	 * 
	 */
	@ApiOperation(value = "Método que cria o produto.")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Criado com sucesso."),
	    @ApiResponse(code = 400, message = "Bad Request: JSON Inválido."),
	    @ApiResponse(code = 422, message = "JSON mal formatado."),
	    @ApiResponse(code = 500, message = "Erro na API.")
	})
	@PostMapping(consumes = { "application/json" }, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<Produto> create(@RequestBody JSONObject jsonObj) {
		try {
			if (produtoService.isJSONValid(jsonObj.toString())) {
				Produto produto = produtoService.create(jsonObj);
				URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(produto.getNome()).build()
						.toUri();

				produtoService.add(produto);
				return ResponseEntity.created(uri).body(null);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			logger.error("JSON inválido. " + e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	/**
	 * 
	 * Método que atualiza o produto.
	 * 
	 * @author Gustavo
	 * 
	 * @param id
	 * @param jsonObj
	 * 
	 * @return Retorna os valores:
	 * 200 – OK: 
	 * 400 - Bad Request: JSON Inválido.
	 * 404 - Not Found.
	 * 422 - JSON mal formatado.
	 * 500 - Erro na API.
	 */
	@ApiOperation(value = "Método que atualiza o produto.")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
	    @ApiResponse(code = 400, message = "Bad Request: JSON Inválido."),
	    @ApiResponse(code = 404, message = "Not Found."),
	    @ApiResponse(code = 422, message = "JSON mal formatado."),
	    @ApiResponse(code = 500, message = "Erro na API.")
	})	
	@PutMapping(path = "/{id}", produces = { "application/json" })
	public ResponseEntity<Produto> update(@PathVariable("id") long id, @RequestBody JSONObject jsonObj) {
		try {
			if (produtoService.isJSONValid(jsonObj.toString())) {
				Produto produto = produtoService.findById(id);
				if (produto == null) {
					logger.error("Produto não encontrado.");
					return ResponseEntity.notFound().build();
				} else {
					produto = produtoService.update(produto, jsonObj);
					return ResponseEntity.ok(produto);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			logger.error("JSON inválido." + e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
}
