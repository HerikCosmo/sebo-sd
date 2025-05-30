package tests;

import communication.BookOutputStream;
import model.Book;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestBookOutputStreamFile {
    public static void main(String[] args) {
        Book[] books = {
                new Book("B001", "The Hitchhiker's Guide to the Galaxy", 15.99, "Sci-fi comedy.", "Used - Like New", "Douglas Adams", "978-0345391803", 1979, "Science Fiction"),
                new Book("B002", "1984", 12.50, "Dystopian novel.", "Used - Good", "George Orwell", "978-0451524935", 1949, "Dystopian"),
                new Book("B003", "Pride and Prejudice", 10.00, "Classic romance.", "New", "Jane Austen", "978-0486284738", 1813, "Romance")
        };

        String filename = "books_data.bin";
        try (FileOutputStream fos = new FileOutputStream(filename);
             BookOutputStream bos = new BookOutputStream(books, 3, fos)) {
            System.out.println(" Escrevendo no arquivo " + filename);
            bos.writeAllBooks();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
