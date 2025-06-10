package middleware;

import service.ProdutoService;
import service.RemoteObjectRef;
import service.RemoteService;
import service.VendasService;

import java.rmi.server.UnicastRemoteObject;
import java.io.IOException;
import java.rmi.RemoteException;

public class RemoteServiceImpl extends UnicastRemoteObject implements RemoteService {
  private final ProdutoService produtoService;
  private final VendasService vendasService;

  public RemoteServiceImpl(ProdutoService ps, VendasService vs) throws RemoteException {
    super();
    this.produtoService = ps;
    this.vendasService = vs;
  }

  @Override
  public byte[] doOperation(RemoteObjectRef o, int methodId, byte[] arguments) throws IOException {
    try {
      Object result = null;
      String object = o.getObjectName();

      if ("ProdutoService".equals(object)) {
        switch (methodId) {
          case 1 -> {
            String id = (String) SerializationUtils.deserialize(arguments);
            result = produtoService.getProductDetails(id);
          }
        }
      } else if ("VendasService".equals(object)) {
        switch (methodId) {
          case 1 -> {
            String id = (String) SerializationUtils.deserialize(arguments);
            result = vendasService.sellProduct(id);
          }
        }
      }

      return SerializationUtils.serialize(result);
    } catch (Exception e) {
      e.printStackTrace();
      return SerializationUtils.serialize("Erro: " + e.getMessage());
    }
  }
}
