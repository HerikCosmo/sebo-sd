package model;

import java.io.Serial;
import java.io.Serializable;

public class Produto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private double price;
    private String description;
    private String condition;

    public Produto(
            String id,
            String title,
            double price,
            String description,
            String condition
    ) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.condition = condition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }
}

