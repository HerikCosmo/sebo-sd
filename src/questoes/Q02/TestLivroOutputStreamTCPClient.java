package questoes.Q02;

import communication.LivroOutputStream;
import model.Livro;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TestLivroOutputStreamTCPClient {
    private static final String SERVER_ADRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        Livro[] livros = {
                new Livro("4", "O Senhor dos Anéis", 25.00, "Fantasia Épica", "Usado", "J.R.R. Tolkien", "978-0618260274", 1954, "Fantasia"),
                new Livro("5", "Duna", 18.75, "Sci-fi clássico.", "Usado", "Frank Herbert", "978-0441172719", 1965, "Ficção Científica")
        };

        try(
                Socket socket = new Socket(SERVER_ADRESS, SERVER_PORT);
                OutputStream os = socket.getOutputStream();
                LivroOutputStream bos = new LivroOutputStream(livros, 2, os)
        ) {

            System.out.println("Conectado ao servidor, enviando dados");
            bos.writeAllBooks();
            System.out.println("Fim");
        } catch( IOException e ) {
            System.out.println("Erro:" + e.getMessage());
        }
    }
}
