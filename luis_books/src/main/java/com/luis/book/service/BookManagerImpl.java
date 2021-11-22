package com.luis.book.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luis.books.persistence.BookDao;

@Service
@Transactional
public class BookManagerImpl implements BookManager{

	@Autowired(required=true)
	private BookDao bookDao;
	
	@Override
	public void eliminarLibro(Integer idBook) {
		bookDao.eliminarLibro(idBook);
	}
	
	public BookDao getBookDao() {
		return bookDao;
	}
	
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

}
