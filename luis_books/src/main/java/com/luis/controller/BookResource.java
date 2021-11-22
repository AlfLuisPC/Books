package com.luis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.luis.book.service.ListBookManager;
import com.luis.books.dto.Book;
import com.luis.books.dto.ListaLibros;

@RestController
@RequestMapping(path ="/libros")
public class BookResource {
	
	@Autowired(required=true)
	ListBookManager listBookManager;

	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/obtenerLista", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Response obtenerListaPorNombreIdUsuario(@QueryParam("nombre") String nombre) {
		Integer idUsuario = (Integer) request.getSession().getAttribute("idUsuario");
		ListaLibros listaLibros =  listBookManager.obtenerListaPorNombreIdUsuario(nombre, idUsuario);
		return Response.status(200).entity(listaLibros).build();
	}
	
	@RequestMapping(value = "/eliminarLista", method = RequestMethod.POST)
	@ResponseBody
	public Response eliminarLista(@QueryParam("idLista") Integer idLista) {
		listBookManager.eliminarLista(idLista);
		return Response.status(200).build();
	}

	@RequestMapping(value = "/obtenerListas", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Response obtenerListasPorIdUsuario() {
		Integer idUsuario = (Integer) request.getSession().getAttribute("idUsuario");
		List<ListaLibros> listaLibros =  listBookManager.obtenerListasPorIdUsuario(idUsuario);
		return Response.status(200).entity(listaLibros).build();
	}

	@RequestMapping(value = "/crearLista", method = RequestMethod.POST)
	@ResponseBody
	public Response crearLista(@QueryParam("nombreLista") String nombreLista) {
		Integer idUsuario = (Integer) request.getSession().getAttribute("idUsuario");
		listBookManager.crearLista(nombreLista, idUsuario);
		return Response.status(200).build();
	}

	@RequestMapping(value = "/eliminarLibro", method = RequestMethod.POST)
	@ResponseBody
	public Response eliminarLibro(@QueryParam("idBook") Integer idBook) {
		listBookManager.eliminarLibro(idBook);
		return Response.status(200).build();
	}
	
	@RequestMapping(value = "/crearLibro", method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseBody
	public Response crearLibro(@RequestBody Book book,@QueryParam("idLista") Integer idLista) {
		listBookManager.crearLibro(book, idLista);
		return Response.status(200).build();
	}

}
