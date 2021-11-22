package com.luis.books.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.luis.books.model.BookEntity;

@Repository
public class BookDao extends GenericRepository<BookEntity, Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5406244620527033453L;
	
	public void eliminarLibro(Integer idBook) {
		try {
			entityManager.createQuery("DELETE t FROM BookEntity t WHERE t.id =:id", BookEntity.class)
			.setParameter("id",idBook).executeUpdate();
		}catch(Exception ex) {
			
		}
	}
	
	public List<BookEntity> libros(Integer idLista) {
		try {
			List<BookEntity> libros = entityManager.createQuery("FROM BookEntity t WHERE t.listBookEntity.id =:id", BookEntity.class)
			.setParameter("id",idLista).getResultList();
			return libros;
		}catch(Exception ex) {
			return null;
		}
	}
	
}
