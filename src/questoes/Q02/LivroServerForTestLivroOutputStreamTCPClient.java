package questoes.Q02;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class LivroServerForTestLivroOutputStreamTCPClient {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor rodando");

            while(true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Cliente conectado: " + socket.getInetAddress());
                    InputStream is = socket.getInputStream();

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while((bytesRead = is.read(buffer)) != -1) {
                        System.out.write(buffer, 0, bytesRead);
                    }

                    System.out.println("Fim");
                } catch (IOException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
