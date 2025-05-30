package communication;

import model.Book;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class BookOutputStream extends OutputStream {
    private final Book[] books;
    private final int objectsQuantity;
    private final OutputStream outputStream;
    private int currentBookIndex = 0;
    private byte[] currentBookBytes = null;
    private int currentByteIndex = 0;

    public BookOutputStream(Book[] books, int objectsQuantity, OutputStream outputStream) {
        if (books == null || outputStream == null) {
            throw new IllegalArgumentException("Array de livros e destino não podem ser nulos.");
        }

        if(objectsQuantity < 0 || objectsQuantity > books.length) {
            throw new IllegalArgumentException("Quantidade de objetos é inválido.");
        }

        this.books = Arrays.copyOf(books, books.length);
        this.objectsQuantity = objectsQuantity;
        this.outputStream = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        throw new UnsupportedOperationException("Não suportado");
    }

    public void writeNextBookData() throws IOException {
        if (currentBookIndex < objectsQuantity) {
            Book book = books[currentBookIndex];
            DataOutputStream dos = new DataOutputStream(outputStream);

            byte[] idBytes = book.getId().getBytes("UTF-8");
            byte[] titleBytes = book.getTitle().getBytes("UTF-8");

            dos.writeInt(idBytes.length);
            dos.writeInt(titleBytes.length);
            dos.writeLong(8);

            dos.write(idBytes);
            dos.write(titleBytes);
            dos.writeDouble(book.getPrice());

            dos.flush();
            currentBookIndex++;
            System.out.println("Livro Enviado: " + book.getTitle());
        } else {
            System.out.println("Não há mais dados para ler");
        }
    }

    public void writeAllBooks() throws IOException {
        while(currentBookIndex < objectsQuantity) {
            writeNextBookData();
        }
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}
