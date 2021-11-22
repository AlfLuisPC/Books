package com.luis.books.model;

import java.util.Date;

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
@Table(name="list_book")
public class ListBookEntity {
	
	private Integer id;
	private String nombreLista;
	private Date fechaCreacion;
	private UsuarioEntity idUsuario;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nombre")
	public String getNombreLista() {
		return nombreLista;
	}

	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}

	@Column(name = "fecha_creacion")
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@JoinColumn(name = "id_usuario")
	@ManyToOne(fetch = FetchType.LAZY)
	public UsuarioEntity getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(UsuarioEntity idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	
	
}
