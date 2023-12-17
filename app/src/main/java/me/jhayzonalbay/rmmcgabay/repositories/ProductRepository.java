package me.jhayzonalbay.rmmcgabay.repositories;

import java.util.ArrayList;
import java.util.List;

import me.jhayzonalbay.rmmcgabay.models.Product;

public class ProductRepository implements CrudRepository<Product> {

    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("Product 1", "Description", 10.00d));
        products.add(new Product("Product 2", "Description", 12.00d));
        products.add(new Product("Product 3", "Description", 15.00d));
        products.add(new Product("Product 4", "Description", 144.00d));
    }

    public ProductRepository(List<Product> products) {
        this.products = products;
    }

    @Override
    public Product insert(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        for (Product item : products) {
            if (item.getName().equals(product.getName())) {
                item = product;
                return item;
            }
        }

        return null;
    }

    @Override
    public Product delete(Product product) {
        products.remove(product);
        return product;
    }

    @Override
    public List<Product> getAll() {
        return products;
    }
}
