package com.luis.books.persistence;

import org.springframework.stereotype.Repository;

import com.luis.books.model.SesionEntity;

@Repository
public class SesionDao extends GenericRepository<SesionEntity,Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8354331194869789777L;

	public SesionEntity obtenerPorToken(String token) {
		try {
			SesionEntity sesionEntity = entityManager.createQuery("FROM SesionEntity t WHERE t.token =:token and t.activo=1", SesionEntity.class)
			.setParameter("token", token)
			.getSingleResult();
			 return sesionEntity;
		}catch(Exception ex) {
			return null;
		}
	}
	
}
