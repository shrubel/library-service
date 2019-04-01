package com.book.library.service;

import com.book.library.domain.Book;
import com.book.library.domain.Books;
import com.book.library.exception.BookStoreException;

public interface BookStoreService {
	
	public Books getAllBooks() throws BookStoreException;
	
	public Book getBook(String bookName) throws BookStoreException;
	
	public int createBooks(Books books) throws BookStoreException;
	
	public int updateBooks(String bookName, Book book) throws BookStoreException;
}
