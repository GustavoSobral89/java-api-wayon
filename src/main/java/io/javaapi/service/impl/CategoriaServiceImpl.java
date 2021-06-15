package io.javaapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.javaapi.model.Categoria;
import io.javaapi.service.CategoriaService;

/**
 * Implementacao das regras de negocio definidas em {@link CategoriaService}.
 * 
 * @author Gustavo
 * 
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

	private List<Categoria> categoriaList;
	
	@Override
	/**
	 * @see CategoriaService#createCategoriaList()
	 */
	public void createCategoriaList() {
		if (categoriaList == null) {
			categoriaList = new ArrayList<Categoria>();
		}
	}

	@Override
	public Categoria add(Categoria categoria) {
		createCategoriaList();
		categoriaList.add(categoria);
		return categoria;
	}

	@Override
	public List<Categoria> find() {
		createCategoriaList();
		return categoriaList;
	}

	@Override
	public Categoria findByNome(String nome) {
		return categoriaList.stream().filter(cat -> 
				cat.getNome().toLowerCase().equals(nome.toLowerCase()))
				.findFirst().orElse(null);
	}

	@Override
	public void delete(Categoria categoria) {
		for(int i=0; i<categoriaList.size();i++){
			if(categoria.getNome().equals(categoriaList.get(i).getNome()))
				categoriaList.remove(i);
		}
	}
}
