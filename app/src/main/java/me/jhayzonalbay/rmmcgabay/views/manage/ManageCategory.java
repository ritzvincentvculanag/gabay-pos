package me.jhayzonalbay.rmmcgabay.views.manage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.Category;
import me.jhayzonalbay.rmmcgabay.models.adapter.CategoryAdapter;
import me.jhayzonalbay.rmmcgabay.repositories.CategoryRepository;
import me.jhayzonalbay.rmmcgabay.utils.Item;
import me.jhayzonalbay.rmmcgabay.views.edit.EditCategory;

public class ManageCategory extends Fragment implements Item {

    private View view;

    private FloatingActionButton addCategory;

    private List<Category> categories;
    private RecyclerView categoriesView;
    private CategoryAdapter categoryAdapter;
    private CategoryRepository repository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_manage_category, container, false);

        addCategory = view.findViewById(R.id.fab_manage_category);
        addCategory.setOnClickListener(e -> {
            Intent goToEditCategory = new Intent(getContext(), EditCategory.class);
            startActivity(goToEditCategory);
        });

        repository = new CategoryRepository(getContext());
        categories = repository.getAll();
        categoryAdapter = new CategoryAdapter(categories, this);
        categoriesView = view.findViewById(R.id.rv_manage_category);
        categoriesView.setAdapter(categoryAdapter);
        categoriesView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void edit(int position) {
        Category categoryToUpdate = categories.get(position);
        Intent goToEditCategory = new Intent(getContext(), EditCategory.class);
        goToEditCategory.putExtra("EXT_CATEGORY", categoryToUpdate);
        startActivity(goToEditCategory);
    }

    @Override
    public void delete(int position) {
        Category categoryToDelete = categories.get(position);
        repository.delete(categoryToDelete);
        categoryAdapter.notifyDataSetChanged();
    }
}
