package questoes.Q04;

import model.Livro;
import model.Produto;
import model.aggregation.Loja;
import questoes.Q04.messages.ReplyMessage;
import questoes.Q04.messages.RequestMessage;
import service.ProdutoService;
import service.VendasService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LivroServer {
    private static final int PORT = 12345;
    private static final int THREAD_POOL_SIZE = 10;
    private final ProdutoService produtoService;
    private final VendasService vendasService;

    public LivroServer() {
        Loja loja = new Loja("SEBO SD", "Rua ABC - 123");

        loja.addProduct(new Livro("1", "O Guia dos Mochileiros das Galáxias", 15.99, "Comédia e Sci-fi", "Usado", "Douglas Adams", "978-0345391803", 1979, "Ficção Científica"));
        loja.addProduct(new Livro("2", "1984", 12.50, "Novela Distópica", "Usado", "George Orwell", "978-0451524935", 1949, "Dystopian"));
        loja.addProduct(new Livro("3", "Orgulho e Preconceito", 10.00, "Romance clássico.", "Novo", "Jane Austen", "978-0486284738", 1813, "Romance"));

        this.produtoService = new ProdutoService(loja);
        this.vendasService = new VendasService(loja);
    }

    public void start() {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            
            while(true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());
                executor.submit(new ClientHandler(clientSocket, produtoService, vendasService));
            }
        } catch (IOException exception) {
            System.out.println("Erro: " + exception.getMessage());
            executor.shutdownNow();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private final ProdutoService produtoService;
        private final VendasService vendasService;

        public ClientHandler(Socket clientSocket, ProdutoService produtoService, VendasService vendasService) {
            this.clientSocket = clientSocket;
            this.produtoService = produtoService;
            this.vendasService = vendasService;
        }

        @Override
        public void run() {
            try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                 ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {

                RequestMessage request = (RequestMessage) ois.readObject();
                System.out.println("Requisição recebida: " + request);

                ReplyMessage reply;
                switch (request.getType()) {
                    case ADD_PRODUCT:
                        produtoService.addProductToInventory(request.getProduct());
                        reply = new ReplyMessage(ReplyMessage.ReplyStatus.SUCCESS, "Produto adicionado com sucesso.");
                        break;
                    case GET_PRODUCT:
                        Produto produto = produtoService.getProductDetails(request.getProdutoId());
                        if (produto != null) {
                            reply = new ReplyMessage(ReplyMessage.ReplyStatus.SUCCESS, "Produto encontrado", produto);
                        } else {
                            reply = new ReplyMessage(ReplyMessage.ReplyStatus.FAILURE, "Produto não encontrado.");
                        }
                        break;
                    case SELL_PRODUCT:
                        if (vendasService.sellProduct(request.getProdutoId())) {
                            reply = new ReplyMessage(ReplyMessage.ReplyStatus.SUCCESS, "Product vendido com sucesso.");
                        } else {
                            reply = new ReplyMessage(ReplyMessage.ReplyStatus.FAILURE, "Erro ao vender produto.");
                        }
                        break;
                    case SEARCH_PRODUCTS:
                        List<Produto> searchResults = produtoService.searchProductsByTitle(request.getSearchKeyword());
                        reply = new ReplyMessage(ReplyMessage.ReplyStatus.SUCCESS, "Produtos encontrados.", searchResults);
                        break;
                    case GET_ALL_BOOKS:
                        List<Livro> allLivros = produtoService.getAllBooks();
                        reply = new ReplyMessage(ReplyMessage.ReplyStatus.SUCCESS, "Produtos encontrados.", (List<Produto>)(List<?>) allLivros); // Cast is safe here
                        break;
                    default:
                        reply = new ReplyMessage(ReplyMessage.ReplyStatus.FAILURE, "Tipo de requisição desconhecida");
                        break;
                }
                // Server must serialize the reply message
                oos.writeObject(reply);
                oos.flush();
                System.out.println("Servidor enviando resposta: " + reply.getStatus());

            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro no cliente: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Erro ao fechar o socket: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        LivroServer server = new LivroServer();
        server.start();
    }

}
