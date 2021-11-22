package com.luis.books.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.luis.books.model.ListBookEntity;

@Repository
public class ListBookDao extends GenericRepository<ListBookEntity,Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2193881827882860085L;

	public ListBookEntity obtenerListaPorNombreIdUsuario(String nombreLista, Integer idUsuario) {
		try {
			ListBookEntity listBookEntity = entityManager.createQuery("FROM ListBookEntity t WHERE t.nombreLista =:nombre AND t.idUsuario.id =:idUsuario", ListBookEntity.class)
			.setParameter("nombre",nombreLista).setParameter("idUsuario",idUsuario)
			.getSingleResult();
			 return listBookEntity;
		}catch(Exception ex) {
			return null;
		}
	}
	
	public List<ListBookEntity> obtenerListasPorIdUsuario(Integer idUsuario) {
		try {
			List<ListBookEntity> listBookEntity = entityManager.createQuery("FROM ListBookEntity t WHERE t.idUsuario.id =:id", ListBookEntity.class)
			.setParameter("id",idUsuario)
			.getResultList();
			 return listBookEntity;
		}catch(Exception ex) {
			return null;
		}
	}
	
	public void eliminarLista(Integer idLista) {
		try {
			entityManager.createQuery("DELETE t FROM BookEntity t WHERE t.listBookEntity.id =:id", ListBookEntity.class)
			.setParameter("id",idLista).executeUpdate();
			entityManager.createQuery("DELETE t FROM ListBookEntity t WHERE t.id =:id", ListBookEntity.class)
			.setParameter("id",idLista).executeUpdate();
		}catch(Exception ex) {
			
		}
	}
	
	
}
