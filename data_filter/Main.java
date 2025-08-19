
package data_filter;

import data_filter.controllers.BookController;
import data_filter.models.Book;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BookController controller = new BookController();
		while (true) {
			System.out.println("\n=== Book Search TUI ===");
			System.out.println("1. List all authors");
			System.out.println("2. List all books by author");
			System.out.println("3. Get book count by author");
			System.out.println("4. List books sorted by rating");
			System.out.println("5. Search books by specific rating");
			System.out.println("6. Exit");
			System.out.print("Choose an option: ");
			String choice = scanner.nextLine();
			try {
				switch (choice) {
					case "1":
						String[] authors = controller.getAllAuthors();
						System.out.println("Authors:");
						for (String a : authors) {
							System.out.println("- " + a);
						}
						break;
					case "2":
						System.out.print("Enter author name: ");
						String authorName = scanner.nextLine();
						Book[] books = controller.getBooksByAuthor(authorName);
						if (books.length == 0) {
							System.out.println("No books found for author.");
						} else {
							System.out.println("Books by " + authorName + ":");
							for (Book b : books) {
								System.out.println("- " + b.getTitle() + " (" + b.getYear() + ", Rating: " + b.getUserRating() + ")");
							}
						}
						break;
					case "3":
						System.out.print("Enter author name: ");
						String countAuthor = scanner.nextLine();
						int count = controller.getBookCountByAuthor(countAuthor);
						System.out.println("Book count for " + countAuthor + ": " + count);
						break;
					case "4":
						Book[] sortedBooks = controller.getBooksByRating();
						System.out.println("Books sorted by rating:");
						for (Book b : sortedBooks) {
							System.out.println("- " + b.getTitle() + " by " + b.getAuthor().getAuthorName() + " (Rating: " + b.getUserRating() + ")");
						}
						break;
					case "5":
						System.out.print("Enter rating (0.0 to 5.0): ");
						String ratingInput = scanner.nextLine();
						int ratingBooks = controller.getBooksByRating(ratingInput);
						if (ratingBooks == 0) {
							System.out.println("No books found with rating " + ratingInput);
						} else {
							System.out.println("Books with rating " + ratingInput + ":");
						}
						break;
					case "6":
						System.out.println("Goodbye!");
						scanner.close();
						return;
					default:
						System.out.println("Invalid option. Please try again.");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
