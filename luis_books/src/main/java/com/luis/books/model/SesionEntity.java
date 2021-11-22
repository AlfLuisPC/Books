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
@Table(name="sesion_usuario")
public class SesionEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer idSesion;
	
	
	@JoinColumn(name = "id_usuario")
	@ManyToOne(fetch = FetchType.LAZY)
	private UsuarioEntity usuario;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="hora_creacion")
	private Date horaCreacion;
	
	@Column(name="fecha_hora_expiracion")
	private Date fechaExpiracion;
	
	@Column(name="activo")
	private Boolean activo;
	
	@Column(name="token")
	private String token;

	public Integer getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(Integer idSesion) {
		this.idSesion = idSesion;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getHoraCreacion() {
		return horaCreacion;
	}

	public void setHoraCreacion(Date horaCreacion) {
		this.horaCreacion = horaCreacion;
	}

	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
