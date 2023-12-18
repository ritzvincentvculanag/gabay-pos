package me.jhayzonalbay.rmmcgabay.views.edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.Category;
import me.jhayzonalbay.rmmcgabay.models.Product;
import me.jhayzonalbay.rmmcgabay.repositories.CategoryRepository;
import me.jhayzonalbay.rmmcgabay.repositories.ProductRepository;
import me.jhayzonalbay.rmmcgabay.utils.Miner;
import me.jhayzonalbay.rmmcgabay.utils.Validator;
import me.jhayzonalbay.rmmcgabay.utils.Widget;

public class EditProduct extends AppCompatActivity implements Widget {

    private TextInputLayout name;
    private TextInputLayout description;
    private TextInputLayout barcode;
    private TextInputLayout price;
    private AutoCompleteTextView category;

    private Button scanBarcode;
    private Button proceed;

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    private Map<String, Category> categoryMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        initWidgets();
        setupCategories();
    }

    @Override
    public void initWidgets() {
        name = findViewById(R.id.til_product_name);
        description = findViewById(R.id.til_product_description);
        category = findViewById(R.id.act_product_category);
        barcode = findViewById(R.id.til_product_barcode);
        price = findViewById(R.id.til_product_price);

        scanBarcode = findViewById(R.id.btn_product_scan);
        proceed = findViewById(R.id.btn_product_edit);
        proceed.setOnClickListener(this::proceedAddAction);

        productRepository = new ProductRepository(this);
        categoryRepository = new CategoryRepository(this);
        categoryMap = new HashMap<>();
    }

    private void proceedAddAction(View view) {
        if (!Validator.fieldsAreValid(name, description, price)) {
            return;
        }

        if (category.getText().toString().isEmpty()) {
            Toast.makeText(this, "Select a category!", Toast.LENGTH_SHORT).show();
            return;
        }

        String productName = Miner.getContent(name);
        String productDescription = Miner.getContent(description);
        String productBarcode = Miner.getContent(barcode);
        Double productPrice = Double.parseDouble(Miner.getContent(price));
        Category productCategory = categoryMap.get(category.getText().toString());
        Product product = new Product(
                productName,
                productDescription,
                productBarcode,
                productPrice,
                productCategory
        );

        productRepository.insert(product);
    }

    private void setupCategories() {
        List<Category> categories = categoryRepository.getAll();
        List<String> categoriesName = categories.stream().map(Category::getName).collect(Collectors.toList());
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(
                this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                categoriesName
        );

        categories.forEach(item -> categoryMap.put(item.getName(), item));
        category.setAdapter(categoriesAdapter);
    }
}