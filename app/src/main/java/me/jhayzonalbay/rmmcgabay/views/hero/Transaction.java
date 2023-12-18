package me.jhayzonalbay.rmmcgabay.views.hero;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.actions.BarcodeScanner;
import me.jhayzonalbay.rmmcgabay.models.Product;
import me.jhayzonalbay.rmmcgabay.models.adapter.ProductAdapter;
import me.jhayzonalbay.rmmcgabay.repositories.ProductRepository;
import me.jhayzonalbay.rmmcgabay.utils.Action;
import me.jhayzonalbay.rmmcgabay.utils.Executable;
import me.jhayzonalbay.rmmcgabay.utils.Widget;

public class Transaction extends Fragment implements Widget, Action {

    private View view;

    // Widgets
    private TextInputLayout search;

    private Button viewCart;

    private FloatingActionButton scan;

    private RecyclerView products;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    // Dependencies
    private ProductRepository productRepository;
    private Executable scanner;

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

        productRepository = new ProductRepository();
        productList = productRepository.getAll();
        productAdapter = new ProductAdapter(productList);

        products = view.findViewById(R.id.rv_trans_products);
        products.setAdapter(productAdapter);
        products.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void initActions() {
        scanner = new BarcodeScanner(getActivity(), this);

        scan.setOnClickListener(e -> scanner.execute());
    }

}