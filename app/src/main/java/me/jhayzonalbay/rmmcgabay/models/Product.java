package me.jhayzonalbay.rmmcgabay.models;

public class Product {

    private Integer id;

    private String name;
    private String description;
    private Double price;
    private Integer quantity;

    public Product() {

    }

    public Product(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void increase() {
        quantity += 1;
    }

    public void decrease() {
        if (quantity == 1) {
            quantity = 1;
        }

        quantity -= 1;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
