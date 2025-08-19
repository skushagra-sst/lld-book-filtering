
package data_filter.controllers;

import data_filter.models.Author;
import data_filter.models.Book;
import data_filter.services.BookService;

public class BookController {
	private BookService bookService;

	public BookController() {
		this.bookService = new BookService();
	}

	// Validate author name input and get book names
	public String[] getBookNamesByAuthor(String authorName) {
		if (authorName == null || authorName.trim().isEmpty()) {
			throw new IllegalArgumentException("Author name cannot be empty");
		}
		Author author = new Author(authorName.trim());
		return bookService.getBookNamesByAuthor(author);
	}

	// Validate author name input and get books
	public Book[] getBooksByAuthor(String authorName) {
		if (authorName == null || authorName.trim().isEmpty()) {
			throw new IllegalArgumentException("Author name cannot be empty");
		}
		Author author = new Author(authorName.trim());
		return bookService.getBooksByAuthor(author);
	}

	// Validate author name input and get book count
	public int getBookCountByAuthor(String authorName) {
		if (authorName == null || authorName.trim().isEmpty()) {
			throw new IllegalArgumentException("Author name cannot be empty");
		}
		Author author = new Author(authorName.trim());
		return bookService.getBookCountByAuthor(author);
	}

	// Get all authors
	public String[] getAllAuthors() {
		return bookService.getAllAuthors();
	}

	// Get books sorted by rating
	public Book[] getBooksByRating() {
		return bookService.getBooksByRating();
	}

	// Get books with specific rating
	public int getBooksByRating(String ratingStr) {
		if (ratingStr == null || ratingStr.trim().isEmpty()) {
			throw new IllegalArgumentException("Rating cannot be empty");
		}
		try {
			double rating = Double.parseDouble(ratingStr.trim());
			if (rating < 0.0 || rating > 5.0) {
				throw new IllegalArgumentException("Rating must be between 0.0 and 5.0");
			}
			return bookService.getBooksByRating(rating);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid rating format. Please enter a valid number.");
		}
	}

	// Example: Validate year input
	public boolean isValidYear(int year) {
		return year > 0 && year <= 2100;
	}
}