package com.book.library;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.book.library.filter.AuthenticationFilter;
import com.book.library.resource.BookResource;

@Component
public class LibraryResourceConfig extends ResourceConfig {
	
	public LibraryResourceConfig() {
		register(BookResource.class);
		register(AuthenticationFilter.class);
	}
	
}
