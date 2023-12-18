package me.jhayzonalbay.rmmcgabay.views.hero;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.utils.Widget;
import me.jhayzonalbay.rmmcgabay.views.Manage;

public class Profile extends Fragment implements Widget {

    private View view;

    private Button manageSystem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);

        initWidgets();

        return view;
    }

    @Override
    public void initWidgets() {
        manageSystem = view.findViewById(R.id.btn_profile_manage);
        manageSystem.setOnClickListener(this::manageSystemAction);
    }

    private void manageSystemAction(View view) {
        Intent goToManage = new Intent(getContext(), Manage.class);
        startActivity(goToManage);
    }
}