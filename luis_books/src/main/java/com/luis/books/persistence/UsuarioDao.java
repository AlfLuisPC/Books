package com.luis.books.persistence;

import org.springframework.stereotype.Repository;

import com.luis.books.model.UsuarioEntity;

@Repository
public class UsuarioDao extends GenericRepository<UsuarioEntity, Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2126458058278099847L;
	
	
	public static final String USUARIOS = "SELECT u FROM UsuarioEntity as u WHERE u.nombre_usuario =:nombreUsuario";

	public static final String USUARIOS_CLAVE = "SELECT u FROM UsuarioEntity as u WHERE u.nombre_usuario =:nombreUsuario AND u.clave_usuario =:claveUsuario";

	
	public boolean consultarUsuario(String nombreUsuario) {
		try {
			UsuarioEntity usuarioEntity = entityManager.createQuery(USUARIOS, UsuarioEntity.class)
					.setParameter("nombreUsuario", nombreUsuario).getSingleResult();
			return usuarioEntity != null ? true : false;
		}catch(Exception ex) {
			return false;
		}
	}
	
	public UsuarioEntity consultarUsuarioLogin(String nombreUsuario, String clave) {
		try {
			UsuarioEntity usuarioEntity = entityManager.createQuery(USUARIOS_CLAVE, UsuarioEntity.class)
					.setParameter("nombreUsuario", nombreUsuario).setParameter("claveUsuario", clave).getSingleResult();
			return usuarioEntity;
		}catch(Exception ex) {
			return null;
		}
	}
	
	

}
