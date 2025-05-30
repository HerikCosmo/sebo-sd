package service;

import model.Livro;
import model.aggregation.Loja;
import model.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoService {
    private final Loja loja;

    public ProdutoService(Loja loja) {
        this.loja = loja;
    }

    public void addProductToInventory(Produto produto) {
        loja.addProduct(produto);
        System.out.println("Produto adicionado: " + produto.getTitle());
    }

    public Produto getProductDetails(String productId) {
        return loja.findProductById(productId);
    }

    public boolean updateProductPrice(String productId, double newPrice) {
        Produto produto = loja.findProductById(productId);
        if(produto != null) {
            produto.setPrice(newPrice);
            System.out.println("Valor do produto" + produto.getTitle() + " atualizado para " + newPrice);
            return true;
        }
        return false;
    }

    public boolean removeProductFromInventory(String productId) {
        if(loja.removeProduct(productId)) {
            System.out.println("Produto removido com sucesso");
            return true;
        }
        System.out.println("Produto não foi encontrado");
        return false;
    }

    public List<Produto> searchProductsByTitle(String keyword) {
        return loja.getInventory().stream()
                .filter(p -> p.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Livro> getAllBooks() {
        return loja.getInventory().stream()
                .filter(p -> p instanceof Livro)
                .map(p -> (Livro) p)
                .collect(Collectors.toList());
    }
}
