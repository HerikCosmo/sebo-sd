package communication;

import model.Livro;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class LivroInputStream extends InputStream {
    private final DataInputStream in;

    public LivroInputStream(InputStream source) {
        if (source == null) {
            throw new IllegalArgumentException("Source é nula");
        }

        this.in = new DataInputStream(source);
    }

    @Override
    public int read() throws IOException {
        throw new UnsupportedOperationException("Função Inválida");
    }

    public Livro readNextLivro() throws IOException {
        try {
            int idLength = in.readInt();
            int titleLength = in.readInt();
            long priceSize = in.readLong();

            if (priceSize != 8) {
                System.err.println("Preço com tamanho de bytes inválido. Tem tamanho " + priceSize);
            }

            byte[] idBytes = new byte[idLength];
            in.readFully(idBytes);
            String id = new String(idBytes, "UTF-8");

            byte[] titleBytes = new byte[titleLength];
            in.readFully(titleBytes);
            String title = new String(titleBytes, "UTF-8");

            double price = in.readDouble();

            return new Livro(id, title, price, "N/A", "N/A", "N/A", "N/A", 0, "N/A");
        } catch (EOFException e) {
            return null;
        }
    }

    public void readAllLivros() throws IOException {
        Livro livro;
        while((livro = readNextLivro()) != null) {
            System.out.println("Livro recebido: " + livro);
        }
    }

    @Override
    public void close() throws IOException {
        in.close();
        super.close();
    }
}
