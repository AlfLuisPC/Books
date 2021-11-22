package com.luis.books.seguridad;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.luis.book.service.UsuarioManagerImpl;
import com.luis.books.dto.Credenciales;


@RestController
@RequestMapping(path ="/registro")
public class UsuarioRegistroResource {

	@Autowired(required=true)
	UsuarioManagerImpl usuarioManager;
	
	@RequestMapping(value = "/usuario", method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseBody
	public Response registroUsuario(@RequestBody Credenciales credenciales) {
		boolean usuario = usuarioManager.registrarUsuario(credenciales);
		if(usuario) {
			return Response.status(200).build();
		}else {
			return Response.status(200).tag("El usuario ya existe").build();			
		}
	}
	
}