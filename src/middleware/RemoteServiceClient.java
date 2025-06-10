package middleware;

import model.Produto;
import service.RemoteObjectRef;
import service.RemoteService;

import java.rmi.Naming;

public class RemoteServiceClient {
  public static void main(String[] args) {
    try {
      RemoteService stub = (RemoteService) Naming.lookup("//localhost/RemoteService");

      RemoteObjectRef ref = new RemoteObjectRef("ProdutoService");
      int methodId = 1;
      byte[] argsBytes = SerializationUtils.serialize("123");

      byte[] resultBytes = stub.doOperation(ref, methodId, argsBytes);
      Produto produto = (Produto) SerializationUtils.deserialize(resultBytes);

      System.out.println("Produto: " + produto.getTitle() + " - R$ " + produto.getPrice());

      ref = new RemoteObjectRef("VendasService");
      methodId = 1;
      argsBytes = SerializationUtils.serialize("123");

      resultBytes = stub.doOperation(ref, methodId, argsBytes);
      boolean vendido = (Boolean) SerializationUtils.deserialize(resultBytes);
      System.out.println("Produto vendido? " + vendido);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
