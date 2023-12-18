package me.jhayzonalbay.rmmcgabay.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.Invoice;
import me.jhayzonalbay.rmmcgabay.models.Product;
import me.jhayzonalbay.rmmcgabay.models.adapter.ProductCheckoutAdapter;
import me.jhayzonalbay.rmmcgabay.utils.Item;
import me.jhayzonalbay.rmmcgabay.utils.Widget;
import me.jhayzonalbay.rmmcgabay.views.hero.Transaction;

public class Checkout extends AppCompatActivity implements Widget, Item {

    private ExtendedFloatingActionButton checkout;

    private List<Product> products;
    private RecyclerView productsView;
    private ProductCheckoutAdapter productAdapter;
    private Invoice invoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        initWidgets();
    }

    @Override
    public void edit(int position) {

    }

    @Override
    public void delete(int position) {
        Product product = products.get(position);
        invoice.setSubTotal(invoice.getSubTotal() - product.getPrice());

        MaterialAlertDialogBuilder alert = new MaterialAlertDialogBuilder(this);

        alert.setTitle("Remove item?");
        alert.setMessage("Are you sure you want to remove the item from the cart?");
        alert.setPositiveButton("Remove", (dialog, which) -> {
            products.remove(product);
            productAdapter.update(products);
        });
        alert.setNegativeButton("Cancel", (dialog, which) -> {});
        alert.show();
    }

    @Override
    public void initWidgets() {
        checkout = findViewById(R.id.fab_checkout_cart);
        checkout.setOnClickListener(e -> {
            if (products.size() == 0) {
                return;
            }

            double subTotal = 0d;
            for (Product product : products) {
                subTotal += product.getPrice() * product.getQuantity();
            }
            invoice.setSubTotal(subTotal);

            Intent intent = new Intent(this, Payment.class);
            intent.putExtra("EXT_INVOICE", invoice);
            startActivity(intent);
        });

        invoice = getIntent().getExtras().getParcelable("EXT_INVOICE");
        products = invoice.getProducts();
        productAdapter = new ProductCheckoutAdapter(products, this);
        productsView = findViewById(R.id.rv_checkout_products);
        productsView.setAdapter(productAdapter);
        productsView.setLayoutManager(new LinearLayoutManager(this));
    }
}