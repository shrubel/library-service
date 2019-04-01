package com.book.library.dao.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.book.library.domain.Book;

public class BookMapper implements RowMapper<Book>{

	@Override
	public Book mapRow(ResultSet rs, int row) throws SQLException {
		Book book = new Book();
		book.setName(rs.getString("bookName"));
		book.setAuthor(rs.getString("author"));
		book.setPublishedDate(rs.getDate("publishedDate"));
		book.setNumberOfBooks(rs.getInt("numberOfBooks"));
		book.setRackNumber(rs.getString("rackNumber"));
		return book;
	}

}
