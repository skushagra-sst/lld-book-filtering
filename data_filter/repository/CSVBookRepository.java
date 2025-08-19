package data_filter.repository;

import data_filter.models.Author;
import data_filter.models.Book;
import data_filter.models.BookType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Comparator;

public class CSVBookRepository implements BookRepository {
    private List<Book> books;
    private static final String CSV_FILE = "data.csv";

    public CSVBookRepository() {
        books = new ArrayList<>();
        loadBooksFromCSV();
    }

    private void loadBooksFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; } // skip header
                String[] fields = parseCSVLine(line);
                if (fields.length < 7) continue;
                try {
                    Book book = new Book();
                    book.setTitle(fields[0]);
                    book.setAuthor(new Author(fields[1]));
                    // Defensive parsing for numeric fields
                    double rating;
                    long reviews;
                    double price;
                    int year;
                    try {
                        rating = Double.parseDouble(fields[2]);
                        reviews = Long.parseLong(fields[3]);
                        price = Double.parseDouble(fields[4]);
                        year = Integer.parseInt(fields[5]);
                    } catch (NumberFormatException nfe) {
                        System.err.println("Skipping line due to number format error: " + line);
                        System.err.println("Parsed fields: ");
                        for (int i = 0; i < fields.length; i++) {
                            System.err.println("  [" + i + "]: " + fields[i]);
                        }
                        continue;
                    }
                    book.setUserRating((int)rating);
                    book.setReviews(reviews);
                    book.setPrice(price);
                    book.setYear(year);
                    book.setBookType(parseBookType(fields[6]));
                    books.add(book);
                } catch (Exception ex) {
                    System.err.println("Skipping malformed line: " + line);
                    ex.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Simple CSV parser to handle quoted fields with commas
    private String[] parseCSVLine(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
                // Don't include the quote character in the result
            } else if (c == ',' && !inQuotes) {
                result.add(sb.toString().trim());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        result.add(sb.toString().trim());
        return result.toArray(new String[0]);
    }

    private BookType parseBookType(String genre) {
        if (genre.equalsIgnoreCase("Fiction")) return BookType.FICTION;
        else return BookType.NON_FICTION;
    }

    @Override
    public String[] getBookNameByAuthor(Author author) {
        List<String> names = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().getAuthorName().equalsIgnoreCase(author.getAuthorName())) {
                names.add(book.getTitle());
            }
        }
        return names.toArray(new String[0]);
    }

    @Override
    public Book[] getBooksByAuthor(Author author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().getAuthorName().equalsIgnoreCase(author.getAuthorName())) {
                result.add(book);
            }
        }
        return result.toArray(new Book[0]);
    }

    @Override
    public int getBookCountByAuthor(Author author) {
        int count = 0;
        for (Book book : books) {
            if (book.getAuthor().getAuthorName().equalsIgnoreCase(author.getAuthorName())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String[] getAllAuthors() {
        Set<String> authors = new HashSet<>();
        for (Book book : books) {
            authors.add(book.getAuthor().getAuthorName());
        }
        return authors.toArray(new String[0]);
    }

    @Override
    public Book[] getBookByRating() {
        List<Book> sorted = new ArrayList<>(books);
        sorted.sort(Comparator.comparingDouble(Book::getUserRating).reversed());
        return sorted.toArray(new Book[0]);
    }

    @Override
    public int getBooksByRating(double rating) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (Math.abs(book.getUserRating() - rating) < 0.01) { // Use small epsilon for double comparison
                result.add(book);
            }
        }
        return result.toArray(new Book[0]).length;
    }
    
}
