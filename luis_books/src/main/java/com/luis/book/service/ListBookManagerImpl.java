package com.luis.book.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luis.books.dto.Book;
import com.luis.books.dto.ListaLibros;
import com.luis.books.model.BookEntity;
import com.luis.books.model.ListBookEntity;
import com.luis.books.model.UsuarioEntity;
import com.luis.books.persistence.BookDao;
import com.luis.books.persistence.ListBookDao;
import com.luis.books.persistence.UsuarioDao;

@Service
@Transactional
public class ListBookManagerImpl implements ListBookManager{

	@Autowired(required=true)
	private ListBookDao listBookDao;
	
	@Autowired(required=true)
	private BookDao bookDao;

	@Autowired(required=true)
	private UsuarioDao usuarioDao;
	
	@Override
	public ListaLibros obtenerListaPorNombreIdUsuario(String nombre, Integer idUsuario) {
		ListBookEntity listBookEntity = listBookDao.obtenerListaPorNombreIdUsuario(nombre, idUsuario);
		ListaLibros listaLibros = new ListaLibros();
		if(listBookEntity != null) {
			List<BookEntity> librosEntities = bookDao.libros(listBookEntity.getId());
			ListaLibros libros = new ListaLibros();
			listaLibros = libros.listaLibro(listBookEntity, librosEntities);
		}
		return listaLibros;
	}
	
	@Override
	public List<ListaLibros> obtenerListasPorIdUsuario(Integer idUsuario) {
		List<ListBookEntity> listBookEntity = listBookDao.obtenerListasPorIdUsuario(idUsuario);
		List<ListaLibros> listaLibros = new ArrayList<>();
		if(listBookEntity != null) {
			for(ListBookEntity list : listBookEntity) {
				List<BookEntity> librosEntities = bookDao.libros(list.getId());
				ListaLibros libro = new ListaLibros();
				libro = libro.listaLibro(list, librosEntities);
				listaLibros.add(libro);
			}
		}
		return listaLibros;

	}

	@Override
	public void crearLista(String nombreLista, Integer idUsuario) {
		try {
			ListBookEntity listBookEntity = new ListBookEntity();
			listBookEntity.setNombreLista(nombreLista);
			listBookEntity.setFechaCreacion(new Date());
			UsuarioEntity usuario = usuarioDao.obtenerPorId(idUsuario);
			listBookEntity.setIdUsuario(usuario);
			listBookDao.crear(listBookEntity);
		}catch(Exception ex) {
			
		}
	}
	
	@Override
	public void eliminarLista(Integer idLista) {
		listBookDao.eliminarLista(idLista);
	}
	
	public void eliminarLibro(Integer idBook) {
		try {
			bookDao.eliminarLibro(idBook);
		}catch(Exception ex) {
			
		}
	}

	public void crearLibro(Book book, Integer idLista) {
		try {
			ListBookEntity listBookEntity = listBookDao.obtenerPorId(idLista);
			BookEntity bookEntity = book.bookEntity(book, listBookEntity);
			bookDao.crear(bookEntity);
		}catch(Exception ex) {
			
		}
	}
	
	

	public ListBookDao getListBookDao() {
		return listBookDao;
	}
	
	public void setListBookDao(ListBookDao listBookDao) {
		this.listBookDao = listBookDao;
	}
	
	public BookDao getBookDao() {
		return bookDao;
	}
	
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

}
