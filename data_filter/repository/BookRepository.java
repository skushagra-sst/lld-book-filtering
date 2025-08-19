package data_filter.repository;

import data_filter.models.Book;
import data_filter.models.Author;

public interface BookRepository {

    public String[] getBookNameByAuthor(Author author);
    public Book[] getBooksByAuthor(Author author);
    public int getBookCountByAuthor(Author author);
    public String[] getAllAuthors();
    public Book[] getBookByRating();
    public int getBooksByRating(double rating);

}
