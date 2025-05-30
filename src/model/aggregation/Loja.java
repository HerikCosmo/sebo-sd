package model.aggregation;

import model.Produto;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Loja implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private List<Produto> inventory;

    public Loja(String name, String address) {
        this.name = name;
        this.address = address;
        this.inventory = new ArrayList<Produto>();
    }

    public void addProduct(Produto produto) {
        this.inventory.add(produto);
    }

    public boolean removeProduct(String productId) {
        return inventory.removeIf(p -> p.getId().equals(productId));
    }

    public Produto findProductById(String productId) {
        return inventory.stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Produto> getInventory() {
        return inventory;
    }

    public void setInventory(List<Produto> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Loja{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}
