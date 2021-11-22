package com.luis.books.dto;

import com.luis.books.model.BookEntity;
import com.luis.books.model.ListBookEntity;

public class Book {

	private Integer id;
	private String googleBookId;
	private String autor;
	private String titulo;
	private String editorial;

	public BookEntity bookEntity(Book book, ListBookEntity listBookEntity) {
		BookEntity bookEntity = new BookEntity();
		bookEntity.setAutor(book.getAutor());
		bookEntity.setEditorial(book.getEditorial());
		bookEntity.setGoogleBookId(book.getGoogleBookId());
		bookEntity.setTitulo(book.getTitulo());
		bookEntity.setListBookEntity(listBookEntity);
		return bookEntity;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoogleBookId() {
		return googleBookId;
	}

	public void setGoogleBookId(String googleBookId) {
		this.googleBookId = googleBookId;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

}
