package service;

import model.Store;
import model.Product;
import model.Exchangable;

public class SalesService {
    private Store store;

    public SalesService(Store store) {
        this.store = store;
    }

    public boolean sellProduct(String productId) {
        Product product = store.findProductById(productId);
        if(product != null) {
            if(store.removeProduct(productId)) {
                System.out.println("Venda: " + product.getTitle() + "por R$" + product.getPrice());
                return true;
            }
        }
        System.out.println("Produto não encontrado");
        return false;
    }

    public String getExchangePolicyForProduct(String productId) {
        Product product = store.findProductById(productId);
        if(product instanceof Exchangable) {
            return ((Exchangable) product).getExchangePolicy();
        } else {
            return "Este produto não pode ser trocado";
        }
    }
}
