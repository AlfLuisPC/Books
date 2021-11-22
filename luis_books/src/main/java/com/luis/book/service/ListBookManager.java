package com.luis.book.service;

import java.util.List;

import com.luis.books.dto.Book;
import com.luis.books.dto.ListaLibros;

public interface ListBookManager {

	public ListaLibros obtenerListaPorNombreIdUsuario(String nombre, Integer idUsuario);
	
	public void eliminarLista(Integer idLista);

	public List<ListaLibros> obtenerListasPorIdUsuario(Integer idUsuario);

	public void crearLista(String nombreLista, Integer idUsuario);

	public void eliminarLibro(Integer idBook);

	public void crearLibro(Book book, Integer idLista);
	
}
