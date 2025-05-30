package questoes.Q04;

import model.Livro;
import questoes.Q04.messages.ReplyMessage;
import questoes.Q04.messages.RequestMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LivroClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public ReplyMessage sendRequest(RequestMessage request) {
        try(
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ) {
            oos.writeObject(request);
            oos.flush();
            System.out.println("Enviando Requisição: " + request.getType());

            ReplyMessage reply = (ReplyMessage) ois.readObject();
            System.out.println("Recebendo Resposta: " + reply.getStatus() + " - " + reply.getMessage());

            return reply;

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro no Cliente: " + e.getMessage());
            return new ReplyMessage(ReplyMessage.ReplyStatus.FAILURE, "Erro de comunicação com o cliente: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        LivroClient client = new LivroClient();

        // 1. Adicionando produto
        Livro newLivro = new Livro("6", "O Marciano", 14.50, "Sci-fi de aventura.", "Novo", "Andy Weir", "978-0804139021", 2011, "Ficção Científica");
        RequestMessage addProductRequest = new RequestMessage(RequestMessage.RequestType.ADD_PRODUCT, newLivro);
        client.sendRequest(addProductRequest);
        System.out.println("---");

        // 2. Buscando um produto
        String produtoId = "6";
        RequestMessage getProductRequest = new RequestMessage(RequestMessage.RequestType.GET_PRODUCT, produtoId);
        ReplyMessage getProductReply = client.sendRequest(getProductRequest);
        if (getProductReply.getStatus() == ReplyMessage.ReplyStatus.SUCCESS) {
            System.out.println(getProductReply.getProduct());
        }
        System.out.println("---");

        // 3. Vendendo um produto
        RequestMessage sellProductRequest = new RequestMessage(RequestMessage.RequestType.SELL_PRODUCT, "2");
        client.sendRequest(sellProductRequest);
        System.out.println("---");

        // 4. Procurar por um produto
        String searchKeyword = "guia";
        RequestMessage searchRequest = new RequestMessage(RequestMessage.RequestType.SEARCH_PRODUCTS, searchKeyword, true);
        ReplyMessage searchReply = client.sendRequest(searchRequest);
        if (searchReply.getStatus() == ReplyMessage.ReplyStatus.SUCCESS && searchReply.getProductList() != null) {
            System.out.println("Pesquisando por '" + searchKeyword + "':");
            searchReply.getProductList().forEach(System.out::println);
        }
        System.out.println("---");

        // 5. Buscando todos os produtos
        RequestMessage getAllBooksRequest = new RequestMessage(RequestMessage.RequestType.GET_ALL_BOOKS);
        ReplyMessage getAllBooksReply = client.sendRequest(getAllBooksRequest);
        if (getAllBooksReply.getStatus() == ReplyMessage.ReplyStatus.SUCCESS && getAllBooksReply.getProductList() != null) {
            System.out.println("Todos os livros:");
            getAllBooksReply.getProductList().forEach(System.out::println);
        }
        System.out.println("---");

        // 6. Procurando um produto que não existe
        RequestMessage getNonExistentProduct = new RequestMessage(RequestMessage.RequestType.GET_PRODUCT, "B999");
        client.sendRequest(getNonExistentProduct);
        System.out.println("---");
    }
}
