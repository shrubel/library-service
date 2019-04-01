package com.book.library;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class LibraryServiceApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		new LibraryServiceApplication().configure(new SpringApplicationBuilder(LibraryServiceApplication.class)).run(args);
	}
}
