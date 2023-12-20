package me.jhayzonalbay.rmmcgabay.views.hero;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.actions.BarcodeScanner;
import me.jhayzonalbay.rmmcgabay.models.Invoice;
import me.jhayzonalbay.rmmcgabay.models.Product;
import me.jhayzonalbay.rmmcgabay.models.adapter.ProductAdapter;
import me.jhayzonalbay.rmmcgabay.repositories.ProductRepository;
import me.jhayzonalbay.rmmcgabay.utils.Action;
import me.jhayzonalbay.rmmcgabay.utils.Executable;
import me.jhayzonalbay.rmmcgabay.utils.ProductCart;
import me.jhayzonalbay.rmmcgabay.utils.Widget;
import me.jhayzonalbay.rmmcgabay.views.Checkout;

public class Transaction extends Fragment implements Widget, Action, ProductCart {

    private View view;

    // Widgets
    private TextInputLayout search;

    private Button viewCart;

    private FloatingActionButton scan;
    private FloatingActionButton clear;

    private RecyclerView products;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    // Dependencies
    private ProductRepository productRepository;
    private Executable scanner;
    private Invoice invoice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_transaction, container, false);

        initWidgets();
        initActions();

        return view;
    }

    @Override
    public void initWidgets() {
        search = view.findViewById(R.id.til_trans_search);

        viewCart = view.findViewById(R.id.btn_trans_cart);

        scan = view.findViewById(R.id.fab_trans_scan);
        clear = view.findViewById(R.id.fab_trans_clear);

        invoice = new Invoice();

        productRepository = new ProductRepository(getContext());
        productList = productRepository.getAll();
        productAdapter = new ProductAdapter(productList, this);

        products = view.findViewById(R.id.rv_trans_products);
        products.setAdapter(productAdapter);
        products.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void initActions() {
        scanner = new BarcodeScanner(getActivity(), this, invoice, viewCart);

        scan.setOnClickListener(e -> scanner.execute());
        clear.setOnClickListener(e -> {
            invoice.clear();
            viewCart.setText(String.valueOf(invoice.getProducts().size()));
        });

        viewCart.setOnClickListener(e -> {
            Intent goToCheckout = new Intent(getContext(), Checkout.class);
            goToCheckout.putExtra("EXT_INVOICE", invoice);
            startActivity(goToCheckout);
        });

        search.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* do nothing */ }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().toLowerCase();

                if (query.isEmpty()) {
                    productAdapter.setProducts(productRepository.getAll());
                    return;
                }

                productAdapter.setProducts(productRepository.search(query));
            }

            @Override
            public void afterTextChanged(Editable s) { /* do nothing */ }
        });
    }

    @Override
    public void add(int position) {
        Product productToAdd = productList.get(position);

        if (invoice.getProducts().contains(productToAdd)) {
            Toast.makeText(getContext(), "Product is already in cart!", Toast.LENGTH_SHORT).show();
            return;
        }

        invoice.setSubTotal(invoice.getSubTotal() + productToAdd.getPrice() * productToAdd.getQuantity());
        invoice.addProduct(productToAdd);
        viewCart.setText(String.valueOf(invoice.getProducts().size()));
    }
}