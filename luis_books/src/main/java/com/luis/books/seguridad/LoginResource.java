package com.luis.books.seguridad;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.luis.book.service.UsuarioManagerImpl;
import com.luis.books.dto.Credenciales;
import com.luis.books.dto.UsuarioSesion;

@RestController
@RequestMapping(path ="/usuario")
public class LoginResource {

	@Autowired(required = true)
	UsuarioManagerImpl usuarioManager;
	
	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Response login(@RequestBody Credenciales credenciales) {
		UsuarioSesion usuarioSesion = usuarioManager.iniciarSesion(credenciales);
		if(usuarioSesion != null) {
			request.getSession().setAttribute("token",usuarioSesion.getToken());
			request.getSession().setAttribute("idUsuario",usuarioSesion.getIdUsuario());
			NewCookie cookie = new NewCookie("Books", usuarioSesion.getToken(), "/", "", "comentario", 7 * 24 * 60 * 60, false);
			return Response.status(200).cookie(cookie).build();
		}else {
			return Response.status(200).tag("El usuario no existe").build();
		}
	}

}
