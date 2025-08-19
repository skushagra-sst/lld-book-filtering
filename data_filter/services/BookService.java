
package data_filter.services;

import data_filter.models.Author;
import data_filter.models.Book;
import data_filter.repository.BookRepository;
import data_filter.repository.CSVBookRepository;

public class BookService {
	private BookRepository bookRepository;

	public BookService() {
		this.bookRepository = new CSVBookRepository();
	}

	public String[] getBookNamesByAuthor(Author author) {
		return bookRepository.getBookNameByAuthor(author);
	}

	public Book[] getBooksByAuthor(Author author) {
		return bookRepository.getBooksByAuthor(author);
	}

	public int getBookCountByAuthor(Author author) {
		return bookRepository.getBookCountByAuthor(author);
	}

	public String[] getAllAuthors() {
		return bookRepository.getAllAuthors();
	}

	public Book[] getBooksByRating() {
		return bookRepository.getBookByRating();
	}

	public int getBooksByRating(double rating) {
		return bookRepository.getBooksByRating(rating);
	}

}
