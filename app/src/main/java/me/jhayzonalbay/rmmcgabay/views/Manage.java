package me.jhayzonalbay.rmmcgabay.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.jhayzonalbay.rmmcgabay.R;

public class Manage extends AppCompatActivity {

    private NavHostFragment content;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        navigation = findViewById(R.id.bnv_manage);
        content = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fcv_manage);

        if (content != null) {
            NavigationUI.setupWithNavController(navigation, content.getNavController());
        }
    }
}