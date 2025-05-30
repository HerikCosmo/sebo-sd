package questoes.Q04.messages;

import model.Produto;

import java.io.Serial;
import java.io.Serializable;

public class RequestMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public enum RequestType { ADD_PRODUCT, GET_PRODUCT, SELL_PRODUCT, SEARCH_PRODUCTS, GET_ALL_BOOKS }
    public RequestType type;
    private String produtoId;
    private Produto produto;
    private String searchKeyword;

    public RequestMessage(RequestType type) {
        this.type = type;
    }

    public RequestMessage(RequestType type, String produtoId) {
        this.type = type;
        this.produtoId = produtoId;
    }

    public RequestMessage(RequestType type, Produto produto) {
        this.type = type;
        this.produto = produto;
    }

    public RequestMessage(RequestType type, String searchKeyword, boolean isSearch) { // Overload for search
        this.type = type;
        this.searchKeyword = searchKeyword;
    }

    public RequestType getType() {
        return type;
    }

    public String getProdutoId() {
        return produtoId;
    }

    public Produto getProduct() {
        return produto;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "type=" + type +
                ", productId='" + produtoId + '\'' +
                ", product=" + produto +
                ", searchKeyword='" + searchKeyword + '\'' +
                '}';
    }
}
