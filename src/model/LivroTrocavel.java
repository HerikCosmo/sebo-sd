package model;

import model.interfaces.Trocavel;

import java.io.Serial;
import java.io.Serializable;

public class LivroTrocavel extends Livro implements Trocavel, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public LivroTrocavel(
            String id,
            String title,
            double price,
            String description,
            String condition,
            String author,
            String isbn,
            int publicationYear,
            String genre
    ) {
        super(id, title, price, description, condition, author, isbn, publicationYear, genre);
    }

    @Override
    public boolean canBeExchanged() {
        return "Usado".equals(getCondition());
    }

    @Override
    public String getExchangePolicy() {
        if (canBeExchanged()) {
            return "Esse livro pode ser trocável até 30 dias depois da compra, desde que esteja em condições boas";
        }

        return "Esse livro não é trocável";
    }
}
