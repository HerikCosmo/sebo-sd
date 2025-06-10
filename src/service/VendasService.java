package service;

import model.Produto;
import model.aggregation.Loja;
import model.interfaces.Trocavel;

import java.util.ArrayList;
import java.util.List;

public class VendasService {
  private Loja loja;
  private List<Produto> vendidos;

  public VendasService(Loja loja) {
    this.loja = loja;
    this.vendidos = new ArrayList<>();
  }

  public boolean sellProduct(String productId) {
    Produto produto = loja.findProductById(productId);
    if (produto != null) {
      if (loja.removeProduct(productId)) {
        System.out.println("Venda: " + produto.getTitle() + " por R$ " + produto.getPrice());
        vendidos.add(produto);
        return true;
      }
    }
    System.out.println("Produto não encontrado");
    return false;
  }

  public String getExchangePolicyForProduct(String productId) {
    Produto produto = loja.findProductById(productId);
    if (produto instanceof Trocavel) {
      return ((Trocavel) produto).getExchangePolicy();
    } else {
      return "Este produto não pode ser trocado";
    }
  }

  // ✅ Método para venda
  public boolean realizarVenda(String id) {
    return sellProduct(id);
  }

  // ✅ Retorna produtos vendidos
  public List<Produto> listarVendidos() {
    return vendidos;
  }

  // ✅ Soma preços dos produtos vendidos
  public double calcularTotal() {
    return vendidos.stream()
        .mapToDouble(Produto::getPrice)
        .sum();
  }
}
