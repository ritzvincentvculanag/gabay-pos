package me.jhayzonalbay.rmmcgabay.views.manage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.Product;
import me.jhayzonalbay.rmmcgabay.models.adapter.ManageProductAdapter;
import me.jhayzonalbay.rmmcgabay.repositories.ProductRepository;
import me.jhayzonalbay.rmmcgabay.utils.Item;
import me.jhayzonalbay.rmmcgabay.utils.Widget;
import me.jhayzonalbay.rmmcgabay.views.edit.EditProduct;

public class ManageProduct extends Fragment implements Widget, Item {

    private View view;

    private TextInputLayout search;

    private List<Product> products;
    private RecyclerView productsView;
    private ManageProductAdapter productAdapter;
    private ProductRepository productRepository;

    private FloatingActionButton addProduct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_manage_product, container, false);

        initWidgets();

        return view;
    }

    @Override
    public void edit(int position) {
        Product productToEdit = products.get(position);
        Intent goToEditProduct = new Intent(getContext(), EditProduct.class);
        goToEditProduct.putExtra("EXT_PRODUCT", productToEdit);
        startActivity(goToEditProduct);
    }

    @Override
    public void delete(int position) {
        Product productToDelete = products.get(position);
        productRepository.delete(productToDelete);
        productAdapter.notifyItemRemoved(position);
    }

    @Override
    public void initWidgets() {
        addProduct = view.findViewById(R.id.fab_mproducts_add);
        addProduct.setOnClickListener(e -> startActivity(new Intent(getContext(), EditProduct.class)));

        productRepository = new ProductRepository(getContext());
        products = productRepository.getAll();
        productAdapter = new ManageProductAdapter(products, this);
        productsView = view.findViewById(R.id.rv_mproducts);
        productsView.setAdapter(productAdapter);
        productsView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}