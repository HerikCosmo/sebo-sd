package middleware;

import model.aggregation.Loja;
import service.ProdutoService;
import service.VendasService;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RemoteServiceServer {
  public static void main(String[] args) {
    try {
      Loja loja = new Loja("Loja Central", "Rua de são nunca");
      ProdutoService ps = new ProdutoService(loja);
      VendasService vs = new VendasService(loja);

      RemoteServiceImpl remoteService = new RemoteServiceImpl(ps, vs);

      LocateRegistry.createRegistry(1099);
      Naming.rebind("RemoteService", remoteService);

      System.out.println("Servidor RMI iniciado com sucesso.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
