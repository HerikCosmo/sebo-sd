package tests;

import communication.BookInputStream;


import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TestBookInputStreamTCPClient {
    private static final String SERVER_ADRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {

        try(
                Socket socket = new Socket(SERVER_ADRESS, SERVER_PORT);
                InputStream is = socket.getInputStream();
                BookInputStream bis = new BookInputStream(is);
        ) {
            System.out.println("Conectado ao servidor, lendo dados");
            bis.readAllBooks();
            System.out.println("Fim");
        } catch( IOException e ) {
            System.out.println("Erro:" + e.getMessage());
        }
    }
}
