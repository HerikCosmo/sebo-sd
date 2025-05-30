package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Store implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private List<Product> inventory;

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
        this.inventory = new ArrayList<Product>();
    }

    public void addProduct(Product product) {
        this.inventory.add(product);
    }

    public boolean removeProduct(String productId) {
        return inventory.removeIf(p -> p.getId().equals(productId));
    }

    public Product findProductById(String productId) {
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

    public List<Product> getInventory() {
        return inventory;
    }

    public void setInventory(List<Product> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}
