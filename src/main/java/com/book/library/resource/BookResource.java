package com.book.library.resource;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.book.library.domain.Book;
import com.book.library.domain.Books;
import com.book.library.exception.BookStoreException;
import com.book.library.service.BookStoreService;

@Path("/library")
@Component
public class BookResource {
	
	@Autowired
	private BookStoreService bookStoreService;
	
	@POST
	@RolesAllowed("ADMIN")
	@Path("create-book")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createBook(Books books) {
		if(books == null || books.getBooks() == null) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Books information should not be empty").build();
		}
		int bookscreatedCount = 0;
		try {
			bookscreatedCount = bookStoreService.createBooks(books);
		} catch (BookStoreException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Unable to create book").build();
		}
		return Response.status(Response.Status.OK).entity("Number of books created : "+bookscreatedCount).build();
	}
	
	@GET
	@Path("/{bookName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBook(@PathParam("bookName") String bookName) {
		if(bookName == null) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Book name should be provided").build();
		}
		Book book = null;
		try {
			book = bookStoreService.getBook(bookName);
			if(book == null) {
				return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Book not found with name "+bookName).build();
			}
		} catch (BookStoreException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
		return Response.status(Response.Status.OK).entity(book).build();
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBooks() {
		Books books = null;
		try {
			books = bookStoreService.getAllBooks();
			if(books == null) {
				return Response.status(Response.Status.NOT_ACCEPTABLE).entity("No book found").build();
			}
		} catch (BookStoreException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
		return Response.status(Response.Status.OK).entity(books).build();
	}
	
	@PUT
	@Path("/{bookName}")
	@RolesAllowed("ADMIN")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBook(@PathParam("bookName") String bookName, Book book) {
		if(bookName == null || bookName.length() == 0 || book == null) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Book info should be provided for updation").build();
		}
		int updatedBook = 0;
		try {
			updatedBook = bookStoreService.updateBooks(bookName, book);
		} catch (BookStoreException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
		String msg = updatedBook > 0 ? "Book info updated " : "Book info not updated ";
		return Response.status(Response.Status.OK).entity(msg+" for book "+bookName).build();

	}
	
}
