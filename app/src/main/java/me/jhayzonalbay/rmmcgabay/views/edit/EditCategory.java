package me.jhayzonalbay.rmmcgabay.views.edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.Category;
import me.jhayzonalbay.rmmcgabay.repositories.CategoryRepository;
import me.jhayzonalbay.rmmcgabay.utils.Miner;
import me.jhayzonalbay.rmmcgabay.utils.Widget;

public class EditCategory extends AppCompatActivity implements Widget {

    private TextInputLayout category;

    private Button proceed;

    private CategoryRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        repository = new CategoryRepository(this);

        initWidgets();
        setupForm();
    }

    @Override
    public void initWidgets() {
        category = findViewById(R.id.til_edit_category);

        proceed = findViewById(R.id.btn_edit_category);
        proceed.setOnClickListener(this::proceedAddAction);
    }

    private void setupForm() {
        if (!getIntent().hasExtra("EXT_CATEGORY")) {
            return;
        }

        Category categoryToUpdate = getIntent().getExtras().getParcelable("EXT_CATEGORY");
        category.getEditText().setText(categoryToUpdate.getName());
        proceed.setText("Update");
        proceed.setOnClickListener(this::proceedEditAction);
    }

    private void proceedAddAction(View view) {
        String categoryName = Miner.getContent(category);

        if (categoryName.isEmpty()) {
            MaterialAlertDialogBuilder alert = new MaterialAlertDialogBuilder(this);

            alert.setTitle("Category error");
            alert.setMessage("Name of category cannot be empty.");
            alert.setPositiveButton("Try again", (dialog, which) -> {});
            alert.show();

            return;
        }

        Category newCategory = new Category(categoryName);
        repository.insert(newCategory);
    }

    private void proceedEditAction(View view) {
        String categoryName = Miner.getContent(category);

        if (categoryName.isEmpty()) {
            MaterialAlertDialogBuilder alert = new MaterialAlertDialogBuilder(this);

            alert.setTitle("Category error");
            alert.setMessage("Name of category cannot be empty.");
            alert.setPositiveButton("Try again", (dialog, which) -> {});
            alert.show();

            return;
        }

        Category categoryToUpdate = getIntent().getExtras().getParcelable("EXT_CATEGORY");
        categoryToUpdate.setName(categoryName);
        repository.update(categoryToUpdate);
    }

}