package com.luis.book.service;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luis.books.dto.Credenciales;
import com.luis.books.dto.UsuarioSesion;
import com.luis.books.exception.BusinessException;
import com.luis.books.exception.ErrorInfo;
import com.luis.books.model.SesionEntity;
import com.luis.books.model.UsuarioEntity;
import com.luis.books.persistence.SesionDao;
import com.luis.books.persistence.UsuarioDao;
import com.luis.books.seguridad.Crypto;


@Service
@Transactional
public class UsuarioManagerImpl implements UsuarioManager{

	@Autowired(required=true)
	private UsuarioDao usuarioDao;	

	@Autowired(required=true)
	private SesionDao sesionDao;	
	
	
	public boolean registrarUsuario(Credenciales credenciales) {
		boolean usuarioRegistrado = false;
		try {
			UsuarioEntity usuario = new UsuarioEntity();
			if (StringUtils.isEmpty(credenciales.getNombreUsuario())) {
				ErrorInfo errorInfo = new ErrorInfo();
				errorInfo.setReferencia("Login");
				errorInfo.setMensaje("El nombre de usuario es requerido");
				throw BusinessException.error(errorInfo);
			}

			if (StringUtils.isEmpty(credenciales.getClaveUsuario())) {
				ErrorInfo errorInfo = new ErrorInfo();
				errorInfo.setReferencia("Login");
				errorInfo.setMensaje("El password es requerido");
				throw BusinessException.error(errorInfo);
			}
			usuario.setNombre_usuario(credenciales.getNombreUsuario());
			String hashPassword = Crypto.hmac(credenciales.getClaveUsuario());
			usuario.setClave_usuario(hashPassword);
			
			if(!usuarioDao.consultarUsuario(credenciales.getNombreUsuario())) {
				usuarioDao.crear (usuario);	
				usuarioRegistrado = true;
			}
			
		}catch(BusinessException e) {
			e.printStackTrace();
		} 
		return usuarioRegistrado;
	}
	
	public UsuarioSesion iniciarSesion(Credenciales credenciales) {
		UsuarioSesion usuarioSesion = null;
		try {
			if (StringUtils.isEmpty(credenciales.getNombreUsuario())) {
				ErrorInfo errorInfo = new ErrorInfo();
				errorInfo.setReferencia("Login");
				errorInfo.setMensaje("El nombre de usuario es requerido");
				throw BusinessException.error(errorInfo);
			}

			if (StringUtils.isEmpty(credenciales.getClaveUsuario())) {
				ErrorInfo errorInfo = new ErrorInfo();
				errorInfo.setReferencia("Login");
				errorInfo.setMensaje("El password es requerido");
				throw BusinessException.error(errorInfo);
			}
			String hashPassword = Crypto.hmac(credenciales.getClaveUsuario());
			UsuarioEntity  usuarioEntity = usuarioDao.consultarUsuarioLogin(credenciales.getNombreUsuario(),hashPassword);
			if(usuarioEntity != null) {
				String token = RandomStringUtils.randomAlphabetic(50);
				
				Integer duracionSegundos = 7 * 24 * 60 * 60; // 7 días.
				DateTime fechaCreacion = DateTime.now();
				DateTime fechaExpiracion = fechaCreacion.plusSeconds(duracionSegundos);

				
				SesionEntity sesionEntity = new SesionEntity();
				sesionEntity.setUsuario(usuarioEntity);
				sesionEntity.setFechaCreacion(fechaCreacion.toDate());
				sesionEntity.setHoraCreacion(fechaCreacion.toDate());
				sesionEntity.setFechaExpiracion(fechaExpiracion.toDate());
				sesionEntity.setActivo(true);
				sesionEntity.setToken(token);
				sesionDao.crear(sesionEntity);
				
				usuarioSesion = new UsuarioSesion();
				usuarioSesion.setToken(token);
				usuarioSesion.setIdUsuario(sesionEntity.getUsuario().getId());
				
			}
		}catch(BusinessException e) {
			e.printStackTrace();
		} 
		return usuarioSesion;		
	}
	
	/**
	 * Getters and Setters
	 *
	 */
	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}
	
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

}
