package com.book.library.dao.helper;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.book.library.domain.Books;

public class BookStoreDaoHelper {
	
	private BookStoreDaoHelper() {}

	public static MapSqlParameterSource[] prepareParams(Books books,String previousBookName) {
		List<MapSqlParameterSource> params = new ArrayList<>();
		books.getBooks().forEach(e -> {
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("bookName", e.getName(), Types.VARCHAR);
			paramSource.addValue("author", e.getAuthor(), Types.VARCHAR);
			paramSource.addValue("publishedDate", e.getPublishedDate(), Types.DATE);
			paramSource.addValue("numberOfBooks", e.getNumberOfBooks(), Types.INTEGER);
			paramSource.addValue("rackNumber", e.getRackNumber(), Types.VARCHAR);
			if(previousBookName != null)
				paramSource.addValue("previousBookName",previousBookName, Types.VARCHAR);
			params.add(paramSource);
		});
		return params.stream().toArray(MapSqlParameterSource[] :: new);
	}
}
