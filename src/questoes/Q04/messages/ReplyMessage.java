package questoes.Q04.messages;

import model.Produto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class ReplyMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public enum ReplyStatus { SUCCESS, FAILURE }
    private ReplyStatus status;
    private String message;
    private Produto produto;
    private List<Produto> produtoList;

    public ReplyMessage(ReplyStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ReplyMessage(ReplyStatus status, String message, Produto produto) {
        this(status, message);
        this.produto = produto;
    }

    public ReplyMessage(ReplyStatus status, String message, List<Produto> produtoList) {
        this(status, message);
        this.produtoList = produtoList;
    }

    public ReplyStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Produto getProduct() {
        return produto;
    }

    public List<Produto> getProductList() {
        return produtoList;
    }

    @Override
    public String toString() {
        return "ReplyMessage{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", product=" + produto +
                ", productList=" + produtoList +
                '}';
    }
}
