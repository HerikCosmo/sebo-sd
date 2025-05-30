package communication;

import model.Book;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class BookInputStream extends InputStream {
    private final DataInputStream in;
    private Book currentBook = null;
    private int bytesReadForCurrentBook = 0;

    public BookInputStream(InputStream source) {
        if (source == null) {
            throw new IllegalArgumentException("source is null");
        }

        this.in = new DataInputStream(source);
    }

    @Override
    public int read() throws IOException {
        throw new UnsupportedOperationException("Função Inválida");
    }

    public Book readNextBook() throws IOException {
        try {
            int idLength = in.readInt();
            int titleLength = in.readInt();
            long priceSize = in.readLong();

            byte[] idBytes = new byte[idLength];
            in.readFully(idBytes);
            String id = new String(idBytes, "UTF-8");

            byte[] titleBytes = new byte[titleLength];
            in.readFully(titleBytes);
            String title = new String(titleBytes, "UTF-8");

            double price = in.readDouble();

            return new Book(id, title, price, "N/A", "N/A", "N/A", "N/A", 0, "N/A");
        } catch (EOFException e) {
            return null;
        }
    }

    public void readAllBooks() throws IOException {
        Book book;
        while((book = readNextBook()) != null) {
            System.out.println("Livro recebido: " + book);
        }
    }

    @Override
    public void close() throws IOException {
        in.close();
        super.close();
    }
}
