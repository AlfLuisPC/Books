package com.luis.books.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class BookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "google_book_id")
	private String googleBookId;

	@Column(name = "autor")
	private String autor;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "editorial")
	private String editorial;

	@JoinColumn(name = "id_list_book")
	@ManyToOne(fetch = FetchType.LAZY)
	private ListBookEntity listBookEntity;

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

	public ListBookEntity getListBookEntity() {
		return listBookEntity;
	}

	public void setListBookEntity(ListBookEntity listBookEntity) {
		this.listBookEntity = listBookEntity;
	}

}
