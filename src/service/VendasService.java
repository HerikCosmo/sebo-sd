package service;

import model.aggregation.Loja;
import model.Produto;
import model.interfaces.Trocavel;

public class VendasService {
    private Loja loja;

    public VendasService(Loja loja) {
        this.loja = loja;
    }

    public boolean sellProduct(String productId) {
        Produto produto = loja.findProductById(productId);
        if(produto != null) {
            if(loja.removeProduct(productId)) {
                System.out.println("Venda: " + produto.getTitle() + " por R$ " + produto.getPrice());
                return true;
            }
        }
        System.out.println("Produto não encontrado");
        return false;
    }

    public String getExchangePolicyForProduct(String productId) {
        Produto produto = loja.findProductById(productId);
        if(produto instanceof Trocavel) {
            return ((Trocavel) produto).getExchangePolicy();
        } else {
            return "Este produto não pode ser trocado";
        }
    }
}
