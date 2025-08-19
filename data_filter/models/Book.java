package data_filter.models;

public class Book {
    String title;
    Author author;
    double userRating;
    long reviews;
    double price;
    int year;
    BookType bookType;

    public Book() {
        this.title = "";
        this.author = null;
        this.userRating = 0;
        this.reviews = 0l;
        this.price = 0;
        this.year = 1970;
        this.bookType = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public long getReviews() {
        return reviews;
    }

    public void setReviews(long reviews) {
        this.reviews = reviews;
    }

    public void setReviews(int reviews) {
        reviews *= 1l;
        this.reviews = reviews;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public Book(String title, Author author, int userRating, long reviews, double price, int year,
            BookType bookType) {
        this.title = title;
        this.author = author;
        this.userRating = userRating;
        this.reviews = reviews;
        this.price = price;
        this.year = year;
        this.bookType = bookType;
    }

}
