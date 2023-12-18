package me.jhayzonalbay.rmmcgabay.views.manage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.views.edit.EditCategory;

public class ManageCategory extends Fragment {

    private View view;

    private FloatingActionButton addCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_manage_category, container, false);

        addCategory = view.findViewById(R.id.fab_manage_category);
        addCategory.setOnClickListener(e -> {
            Intent goToEditCategory = new Intent(getContext(), EditCategory.class);
            startActivity(goToEditCategory);
        });

        return view;
    }
}