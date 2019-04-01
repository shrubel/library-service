package com.book.library.dao;

import com.book.library.domain.Book;
import com.book.library.domain.Books;

public interface BookStoreDao {
	
	public Books getAllBooks();
	
	public Book getBook(String bookName);
	
	public int createBooks(Books books);
	
	public int updateBooks(String bookName, Book book);
	
}
