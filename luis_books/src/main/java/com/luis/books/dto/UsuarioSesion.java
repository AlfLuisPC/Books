package com.luis.books.dto;

public class UsuarioSesion {

	String token;
	Integer idUsuario;
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
}
