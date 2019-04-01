package com.book.library.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.book.library.dao.helper.BookMapper;
import com.book.library.dao.helper.BookStoreDaoHelper;
import com.book.library.domain.Book;
import com.book.library.domain.Books;

@Repository
public class BookStoreDaoImpl implements BookStoreDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final  String GET_ALL_BOOKS = "select bookName, author, publishedDate, numberOfBooks, rackNumber from Book";
	private static final String GET_BOOK = "select bookName, author, publishedDate, numberOfBooks, rackNumber from Book where bookName = :bookName";
	private static final String CREATE_BOOKS = "insert into Book(bookName, author, publishedDate, numberOfBooks, rackNumber) values(:bookName,:author,:publishedDate,:numberOfBooks,:rackNumber)";
	private static final String UPDATE_BOOK = "update Book set bookName = :bookName, author = :author, publishedDate = :publishedDate, numberOfBooks = :numberOfBooks, rackNumber=:rackNumber where bookName = :previousBookName";
	
	private NamedParameterJdbcTemplate getNamedJdbcTemplate() {
		return new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	@Override
	public Books getAllBooks() {
		List<Book> bookList = getNamedJdbcTemplate().query(GET_ALL_BOOKS, new BookMapper());
		Books books = new Books();
		books.setBooks(bookList);
		return books;
	}

	@Override
	public Book getBook(String bookName) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("bookName", bookName);
		List<Book> bookList = getNamedJdbcTemplate().query(GET_BOOK, params, new BookMapper());
		if(bookList != null && !bookList.isEmpty()) {
			return bookList.get(0);
		}
		return null;
	}

	@Override
	public int createBooks(Books books) {
		MapSqlParameterSource[] params = BookStoreDaoHelper.prepareParams(books,null);
		int[] updatedRows = getNamedJdbcTemplate().batchUpdate(CREATE_BOOKS, params);
		return updatedRows.length;
	}

	@Override
	public int updateBooks(String bookName, Book book) {
		List<Book> bookList = new ArrayList<>();
		bookList.add(book);
		Books books = new Books();
		books.setBooks(bookList);
		MapSqlParameterSource[] params = BookStoreDaoHelper.prepareParams(books, bookName);
		return getNamedJdbcTemplate().update(UPDATE_BOOK, params[0]);
	}
	
}
