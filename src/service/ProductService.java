package service;

import model.Store;
import model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private Store store;

    public ProductService(Store store) {
        this.store = store;
    }

    public void addProductToInventory(Product product) {
        store.addProduct(product);
        System.out.println("Produto adicionado: " + product.getTitle());
    }

    public Product getProductDetails(String productId) {
        return store.findProductById(productId);
    }

    public boolean updateProductPrice(String productId, double newPrice) {
        Product product = store.findProductById(productId);
        if(product != null) {
            product.setPrice(newPrice);
            System.out.println("Valor do produto" + product.getTitle() + " atualizado para " + newPrice);
            return true;
        }
        return false;
    }

    public boolean removeProductFromInventory(String productId) {
        if(store.removeProduct(productId)) {
            System.out.println("Produto removido com sucesso");
            return true;
        }
        System.out.println("Produto não foi encontrado");
        return false;
    }

    public List<Product> searchProductsByTitle(String keyword) {
        return store.getInventory().stream()
                .filter(p -> p.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
