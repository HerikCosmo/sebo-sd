package tests;

import communication.BookOutputStream;
import model.Book;


import java.io.IOException;

public class TestBookOutputStreamSystemOut {
    public static void main(String[] args) {
        Book[] books = {
                new Book("B001", "The Hitchhiker's Guide to the Galaxy", 15.99, "Sci-fi comedy.", "Used - Like New", "Douglas Adams", "978-0345391803", 1979, "Science Fiction"),
                new Book("B002", "1984", 12.50, "Dystopian novel.", "Used - Good", "George Orwell", "978-0451524935", 1949, "Dystopian"),
                new Book("B003", "Pride and Prejudice", 10.00, "Classic romance.", "New", "Jane Austen", "978-0486284738", 1813, "Romance")
        };

        try (BookOutputStream bos = new BookOutputStream(books, 3, System.out)) {
            System.out.println("System.out");
            bos.writeAllBooks();
            System.out.println("Acabouse");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
