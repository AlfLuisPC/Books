package com.luis.books.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.luis.books.model.BookEntity;
import com.luis.books.model.ListBookEntity;

public class ListaLibros {

	private Integer idLista;
	private String nombreLista;
	private Date fechaCreacion;
	private Integer idUsuario;
	private List<Book> libros;
	
	
	public List<ListaLibros> listaLibros(List<ListBookEntity> listBookEntities, List<BookEntity> libros){
		List<ListaLibros> listaLibros = new ArrayList<>();
		for(ListBookEntity lista : listBookEntities) {
			ListaLibros listaLibro = new ListaLibros();
			listaLibro.setIdLista(lista.getId());
			listaLibro.setNombreLista(lista.getNombreLista());
			listaLibro.setFechaCreacion(lista.getFechaCreacion());
			listaLibro.setIdUsuario(lista.getIdUsuario().getId());
			List<Book> librosLista = new ArrayList<>();
			if(libros != null) {
				for(BookEntity book : libros) {
					Book libro = new Book();
					libro.setId(book.getId());
					libro.setGoogleBookId(book.getGoogleBookId());
					libro.setAutor(book.getAutor());
					libro.setTitulo(book.getTitulo());
					libro.setEditorial(book.getEditorial());
					librosLista.add(libro);
					listaLibro.setLibros(librosLista);
				}
			}
			listaLibros.add(listaLibro);
		}		
		return listaLibros;
	}
	
	public ListaLibros listaLibro(ListBookEntity lista, List<BookEntity> libros){
			ListaLibros listaLibro = new ListaLibros();
			listaLibro.setIdLista(lista.getId());
			listaLibro.setNombreLista(lista.getNombreLista());
			listaLibro.setFechaCreacion(lista.getFechaCreacion());
			listaLibro.setIdUsuario(lista.getIdUsuario().getId());
			List<Book> librosLista = new ArrayList<>();
			if(libros != null) {
				for(BookEntity book : libros) {
					Book libro = new Book();
					libro.setId(book.getId());
					libro.setGoogleBookId(book.getGoogleBookId());
					libro.setAutor(book.getAutor());
					libro.setTitulo(book.getTitulo());
					libro.setEditorial(book.getEditorial());
					librosLista.add(libro);
					listaLibro.setLibros(librosLista);
				}
			}
		return listaLibro;
	}

	public Integer getIdLista() {
		return idLista;
	}

	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}

	public String getNombreLista() {
		return nombreLista;
	}

	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<Book> getLibros() {
		return libros;
	}

	public void setLibros(List<Book> libros) {
		this.libros = libros;
	}

}
