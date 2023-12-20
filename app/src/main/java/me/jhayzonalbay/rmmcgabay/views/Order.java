package me.jhayzonalbay.rmmcgabay.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.Product;
import me.jhayzonalbay.rmmcgabay.models.adapter.ProductAdapter;
import me.jhayzonalbay.rmmcgabay.repositories.ProductRepository;
import me.jhayzonalbay.rmmcgabay.utils.ProductCart;

public class Order extends AppCompatActivity implements ProductCart {

    private RecyclerView products;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private ProductRepository productRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        productRepository = new ProductRepository(this);
        productList = productRepository.getAll();
        productAdapter = new ProductAdapter(productList, this);

        products = findViewById(R.id.rv_order_products);
        products.setAdapter(productAdapter);
        products.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public void add(int position) {

    }
}