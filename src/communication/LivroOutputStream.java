package communication;

import model.Livro;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class LivroOutputStream extends OutputStream {
  private final Livro[] livros;
  private final int objectsQuantity;
  private final OutputStream outputStream;
  private int currentLivroIndex = 0;

  public LivroOutputStream(Livro[] livros, int objectsQuantity, OutputStream outputStream) {
    if (livros == null || outputStream == null) {
      throw new IllegalArgumentException("Array de livros e destino não podem ser nulos.");
    }

    if (objectsQuantity < 0 || objectsQuantity > livros.length) {
      throw new IllegalArgumentException("Quantidade de objetos é inválido.");
    }

    this.livros = Arrays.copyOf(livros, livros.length);
    this.objectsQuantity = objectsQuantity;
    this.outputStream = outputStream;
  }

  @Override
  public void write(int b) throws IOException {
    throw new UnsupportedOperationException("Função Inválida");
  }

  public void writeNextBookData() throws IOException {
    if (currentLivroIndex < objectsQuantity) {
      Livro livro = livros[currentLivroIndex];
      DataOutputStream dos = new DataOutputStream(outputStream);

      byte[] idBytes = livro.getId().getBytes("UTF-8");
      byte[] titleBytes = livro.getTitle().getBytes("UTF-8");

      dos.writeInt(idBytes.length);
      dos.writeInt(titleBytes.length);
      dos.writeLong(8);

      dos.write(idBytes);
      dos.write(titleBytes);
      dos.writeDouble(livro.getPrice());

      dos.flush();
      currentLivroIndex++;
      System.out.println("Livro Enviado: " + livro);
    } else {
      System.out.println("Não há dados para ler");
    }
  }

  public void writeAllBooks() throws IOException {
    while (currentLivroIndex < objectsQuantity) {
      writeNextBookData();
    }
  }

  @Override
  public void close() throws IOException {
    super.close();
  }
}
