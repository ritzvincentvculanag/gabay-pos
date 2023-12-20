package me.jhayzonalbay.rmmcgabay.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.stream.Collectors;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.actions.BarcodeScanner;
import me.jhayzonalbay.rmmcgabay.models.Category;
import me.jhayzonalbay.rmmcgabay.models.Invoice;
import me.jhayzonalbay.rmmcgabay.models.Product;
import me.jhayzonalbay.rmmcgabay.models.adapter.ProductAdapter;
import me.jhayzonalbay.rmmcgabay.repositories.CategoryRepository;
import me.jhayzonalbay.rmmcgabay.repositories.ProductRepository;
import me.jhayzonalbay.rmmcgabay.utils.Action;
import me.jhayzonalbay.rmmcgabay.utils.Executable;
import me.jhayzonalbay.rmmcgabay.utils.ProductCart;

public class Order extends AppCompatActivity implements ProductCart, Action {

    private Button cart;
    private AutoCompleteTextView filter;
    private RecyclerView products;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private Executable scanner;
    private Invoice invoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        productRepository = new ProductRepository(this);
        categoryRepository = new CategoryRepository(this);
        scanner = new BarcodeScanner(this, this);
        invoice = new Invoice();

        productList = productRepository.getAll();
        productAdapter = new ProductAdapter(productList, this);

        cart = findViewById(R.id.btn_order_cart);
        filter = findViewById(R.id.actv_order_filter);
        products = findViewById(R.id.rv_order_products);
        products.setAdapter(productAdapter);
        products.setLayoutManager(new GridLayoutManager(this, 2));

        initActions();
        setupProductCategories();
    }

    @Override
    public void add(int position) {
        Product productToAdd = productList.get(position);

        if (invoice.getProducts().contains(productToAdd)) {
            Toast.makeText(this, "Product is already in cart!", Toast.LENGTH_SHORT).show();
            return;
        }

        invoice.setSubTotal(invoice.getSubTotal() + productToAdd.getPrice() * productToAdd.getQuantity());
        invoice.addProduct(productToAdd);
        cart.setText(String.valueOf(invoice.getProducts().size()));
    }

    @Override
    public void initActions() {
        filter.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCategory = filter.getText().toString();
            List<Product> filteredProducts = productList.stream()
                    .filter(product -> product.getCategory().getName().equals(selectedCategory))
                    .collect(Collectors.toList());

            productAdapter.setProducts(filteredProducts);
        });
    }

    private void setupProductCategories() {
        List<Category> categories = categoryRepository.getAll();
        List<String> categoriesName = categories.stream().map(Category::getName).collect(Collectors.toList());
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(
                this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                categoriesName
        );

        filter.setAdapter(categoriesAdapter);
    }
}