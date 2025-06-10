
package middleware;

import middleware.messages.RequestMessage;
import middleware.messages.ReplyMessage;
import model.Produto;
import service.ProdutoService;
import service.RemoteObjectRef;
import service.RemoteService;
import service.VendasService;

import java.io.*;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RemoteServiceImpl extends UnicastRemoteObject implements RemoteService {

  private final ProdutoService produtoService;
  private final VendasService vendasService;

  public RemoteServiceImpl(ProdutoService produtoService, VendasService vendasService) throws RemoteException {
    this.produtoService = produtoService;
    this.vendasService = vendasService;
  }

  // Método principal do RMI
  @Override
  public byte[] doOperation(RemoteObjectRef o, int methodId, byte[] arguments) {
    try {
      // Reconstrói a requisição
      RequestMessage req = new RequestMessage(0, 1, o.getServiceName(), methodId, arguments);
      System.out.println("Recebida request: " + req.getObjectReference() + " - método: " + methodId);

      // Decodifica e executa a operação
      byte[] result = dispatch(req);

      // Encapsula a resposta
      ReplyMessage reply = new ReplyMessage(1, req.getRequestId(), result);
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(bout);
      oos.writeObject(reply);
      return bout.toByteArray();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  // Apenas uma simulação de leitura da requisição
  public byte[] getRequest() {
    System.out.println("Esperando requisição do cliente...");
    return new byte[0]; // Simulação
  }

  // Simulação do envio da resposta para o cliente
  public void sendReply(ReplyMessage reply, InetAddress clientHost, int clientPort) {
    System.out.println("Enviando resposta para " + clientHost + ":" + clientPort);
    System.out.println("Reply ID: " + reply.getRequestId());
  }

  // Mapeia os métodos
  private byte[] dispatch(RequestMessage req) throws Exception {
    ByteArrayInputStream bin = new ByteArrayInputStream(req.getArguments());
    ObjectInputStream ois = new ObjectInputStream(bin);

    switch (req.getMethodId()) {
      case 0: { // Buscar produto por ID
        String id = (String) ois.readObject();
        Produto p = produtoService.getProdutoPorId(id);
        return serializeObject(p);
      }
      case 1: { // Listar todos os produtos
        List<Produto> produtos = produtoService.listarProdutos();
        return serializeObject(produtos);
      }
      case 2: { // Remover produto
        String id = (String) ois.readObject();
        boolean removed = produtoService.removerProduto(id);
        return serializeObject(removed);
      }
      case 3: { // Realizar venda
        String id = (String) ois.readObject();
        boolean sucesso = vendasService.realizarVenda(id);
        return serializeObject(sucesso);
      }
      case 4: { // Listar vendidos
        List<Produto> vendidos = vendasService.listarVendidos();
        return serializeObject(vendidos);
      }
      case 5: { // Calcular total
        double total = vendasService.calcularTotal();
        return serializeObject(total);
      }
      default:
        throw new IllegalArgumentException("Método não encontrado");
    }
  }

  // Serializa resultado para enviar de volta
  private byte[] serializeObject(Object obj) throws IOException {
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(bout);
    oos.writeObject(obj);
    return bout.toByteArray();
  }
}
