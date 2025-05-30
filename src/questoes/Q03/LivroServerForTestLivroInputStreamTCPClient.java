package questoes.Q03;

import communication.LivroOutputStream;
import model.Livro;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class LivroServerForTestLivroInputStreamTCPClient {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            Livro[] livros = {
                    new Livro("4", "O Senhor dos Anéis", 25.00, "Fantasia Épica", "Usado", "J.R.R. Tolkien", "978-0618260274", 1954, "Fantasia"),
                    new Livro("5", "Duna", 18.75, "Sci-fi clássico.", "Usado", "Frank Herbert", "978-0441172719", 1965, "Ficção Científica")
            };

            while(true) {
                System.out.println("Servidor rodando");

                try(Socket socket = serverSocket.accept()) {
                    System.out.println("Cliente conectado: " + socket.getInetAddress());

                    try(
                            OutputStream os = socket.getOutputStream();
                            LivroOutputStream bos = new LivroOutputStream(livros, 2, os)
                    ) {
                        System.out.println("Conectado ao servidor, enviando dados...");
                        bos.writeAllBooks();
                        System.out.println("Dados enviados.");
                    } catch( IOException e ) {
                        System.out.println("Erro: " + e.getMessage());
                    }
            }
        }
    } catch(IOException e) {
            System.out.println("Erro: " + e.getMessage());
    }
}

}
