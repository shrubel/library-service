package com.book.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.book.library.dao.BookStoreDao;
import com.book.library.domain.Book;
import com.book.library.domain.Books;
import com.book.library.exception.BookStoreException;

@Service
public class BookStoreServiceImpl implements BookStoreService {

	@Autowired
	private BookStoreDao bookStoreDao;
	
	@Override
	public Books getAllBooks() throws BookStoreException {
		try {
			return bookStoreDao.getAllBooks();
		} catch (Exception e) {
			throw new BookStoreException("Unable to get books info");
		}
	}

	@Override
	public Book getBook(String bookName) throws BookStoreException{
		try {
			return bookStoreDao.getBook(bookName);
		} catch (Exception e) {
			throw new BookStoreException("Unable to get the book with name :"+bookName);
		}
	}

	@Override
	public int createBooks(Books books) throws BookStoreException {
		try {
			return bookStoreDao.createBooks(books);
		} catch (DuplicateKeyException e) {
			throw new BookStoreException("Duplicate book name,please provide unique name for each book");
		} catch (Exception e) {
			throw new BookStoreException("Unable to create book");
		}
	}

	@Override
	public int updateBooks(String bookName,Book book) throws BookStoreException {
		try {
			return bookStoreDao.updateBooks(bookName, book);
		} catch (Exception e) {
			throw new BookStoreException("Unable to update the book");
		}
	}
	
}
