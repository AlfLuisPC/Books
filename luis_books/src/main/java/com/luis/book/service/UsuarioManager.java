package com.luis.book.service;

import com.luis.books.dto.Credenciales;
import com.luis.books.dto.UsuarioSesion;

public interface UsuarioManager {

	public boolean registrarUsuario(Credenciales credenciales);

	public UsuarioSesion iniciarSesion(Credenciales credenciales);
	
}
