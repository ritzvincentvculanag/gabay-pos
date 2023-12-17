package me.jhayzonalbay.rmmcgabay.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.adapter.ProductAdapter;
import me.jhayzonalbay.rmmcgabay.repositories.ProductRepository;
import me.jhayzonalbay.rmmcgabay.utils.Widget;

public class Transaction extends Fragment implements Widget {

    private View view;

    // Widgets
    private TextInputLayout search;

    private Button cart;

    private RecyclerView products;
    private ProductAdapter productAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_transaction, container, false);

        initWidgets();

        return view;
    }

    @Override
    public void initWidgets() {
        search = view.findViewById(R.id.til_trans_search);

        cart = view.findViewById(R.id.btn_trans_cart);

        productAdapter = new ProductAdapter(new ProductRepository());

        products = view.findViewById(R.id.rv_trans_products);
        products.setAdapter(productAdapter);
        products.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}