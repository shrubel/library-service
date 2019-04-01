package com.book.library.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "book")
public class Book {
	
	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "author")
	private String author;
	
	@XmlAttribute(name = "publishedDate")
	private Date publishedDate;
	
	@XmlAttribute(name = "numberOfBooks")
	private int numberOfBooks;
	
	@XmlAttribute(name = "rackNumber")
	private String rackNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public void setNumberOfBooks(int numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}

	public String getRackNumber() {
		return rackNumber;
	}

	public void setRackNumber(String rackNumber) {
		this.rackNumber = rackNumber;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + ", publishedDate=" + publishedDate + ", numberOfBooks="
				+ numberOfBooks + ", rackNumber=" + rackNumber + "]";
	}

}
