package io.javaapi.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response;
	private String nome;

	public RecursoCriadoEvent(Object source, HttpServletResponse response, String nome) {
		super(source);
		this.response = response;
		this.nome = nome;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public String getNome() {
		return nome;
	}
}