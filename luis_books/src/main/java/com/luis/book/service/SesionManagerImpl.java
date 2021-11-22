package com.luis.book.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luis.books.model.SesionEntity;
import com.luis.books.persistence.SesionDao;

@Service
@Transactional
public class SesionManagerImpl implements SesionManager{


	@Autowired(required=true)
	private SesionDao sesionDao;
	
	@Override
	public String existeUsuarioSesion(String token) {
		SesionEntity sesionEntity = sesionDao.obtenerPorToken(token);
		String tokenValido = "";
		if(sesionEntity != null) {
			tokenValido = sesionEntity.getToken();
		}
		return tokenValido;
	}

}
