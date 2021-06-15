package io.javaapi.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.javaapi.event.RecursoCriadoEvent;
import io.javaapi.model.Categoria;
import io.javaapi.service.CategoriaService;
import io.swagger.annotations.ApiOperation;

/**
 * SpringBoot RestController que cria todos os end points de serviço relacionados a categorias.
 * 
 * @author Gustavo
 *
 */
@RestController
@RequestMapping("/java-api/categorias")
public class CategoriaController {
	
//	private static final Logger logger = Logger.getLogger(CategoriaController.class);
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@ApiOperation(value = "Lista as categorias.")
	@GetMapping
	public List<Categoria> listar() {
		return categoriaService.find();
	}

	@PostMapping
	@ApiOperation(value = "Cria uma categoria.")
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaService.add(categoria);
		//o publicador do spring vai chamar um evento
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getNome()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{nome}")
//			.buildAndExpand(categoriaSalva.getNome()).toUri();
//		response.setHeader("Location", uri.toASCIIString());
	}
	
	@GetMapping("/{nome}")
	@ApiOperation(value = "Busca uma categoria por nome.")
	public ResponseEntity<Categoria> buscarPeloNome(@PathVariable String nome) {
		Categoria categoria = categoriaService.findByNome(nome);
		return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
		//retorna ok mas com lista vazia, o correto
		//noContent não entra pois retorna algo: lista vazia
	}
	
	@DeleteMapping()
	@ApiOperation(value = "Deleta uma categoria.")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@Valid @RequestBody Categoria categoria) {
		categoriaService.delete(categoria);
	}
}