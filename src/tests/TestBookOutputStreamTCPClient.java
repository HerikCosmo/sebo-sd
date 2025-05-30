package tests;

import communication.BookOutputStream;
import model.Book;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TestBookOutputStreamTCPClient {
    private static final String SERVER_ADRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        Book[] books = {
                new Book("B004", "The Lord of the Rings", 25.00, "Fantasy epic.", "Used - Excellent", "J.R.R. Tolkien", "978-0618260274", 1954, "Fantasy"),
                new Book("B005", "Dune", 18.75, "Classic sci-fi.", "Used - Good", "Frank Herbert", "978-0441172719", 1965, "Science Fiction")
        };

        try(
                Socket socket = new Socket(SERVER_ADRESS, SERVER_PORT);
                OutputStream os = socket.getOutputStream();
                BookOutputStream bos = new BookOutputStream(books, 2, os)
        ) {

            System.out.println("Conectado ao servidor, enviando dados");
            bos.writeAllBooks();
            System.out.println("Fim");
        } catch( IOException e ) {
            System.out.println("Erro:" + e.getMessage());
        }
    }
}
