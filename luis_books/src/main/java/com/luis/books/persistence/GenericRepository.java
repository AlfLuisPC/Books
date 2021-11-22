package com.luis.books.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.luis.books.exception.BusinessException;


public class GenericRepository<T, K extends Serializable> implements Repository<T, K> {

	private static final long serialVersionUID = -8198863493714030745L;

	@PersistenceContext(name = "bookPU")
	protected EntityManager entityManager;

	protected Class<T> classType;

	@SuppressWarnings("unchecked")
	public GenericRepository() {
		classType = (Class<T>) getParameterClass(getClass(), 0);
	}

	@Override
	public T crear(T entity) {
		entityManager.persist(entity);
		return entity;
	}
	
	//del generic de Luis
    public List<T> obtenerTodos() {
        return consultarPaginado(true, 0, 0);
    }

	@Override
	public T obtenerPorId(K key) {
		return entityManager.find(classType, key);
	}

	@Override
	public List<T> consultarTodos() {
		return consultarPaginado(true, 0, 0);
	}

	@Override
	public List<T> consultarPaginado(int cantidadResultados, int primerResultado) {
		return consultarPaginado(false, cantidadResultados, primerResultado);
	}

	private List<T> consultarPaginado(boolean todo, int cantidadResultados, int primerResultado) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(classType);
		Root<T> rt = cq.from(classType);
		cq.select(rt);
		TypedQuery<T> query = entityManager.createQuery(cq);

		if (!todo) {
			query.setMaxResults(cantidadResultados);
			query.setFirstResult(primerResultado);
		}

		return query.getResultList();
	}

	@Override
	public T actualizar(T entity) {
		return entityManager.merge(entity);
	}

	@Override
	public void eliminarPorId(K key) {
		T entity = obtenerPorId(key);

		if (entity != null) {
			eliminar(entity);
		} else {
			throw new BusinessException("Persistence", "La eliminación no se ha realizado porque no exite un entidad con el ID indicado.");
		}
	}

	@Override
	public void eliminar(T entity) {
		entityManager.remove(entity);
	}

	private static Class<?> getParameterClass(Class<?> clazz, int index) {
		return (Class<?>) (((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[index]);
	}
}
