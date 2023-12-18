package me.jhayzonalbay.rmmcgabay.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.db.Database;
import me.jhayzonalbay.rmmcgabay.models.UserType;
import me.jhayzonalbay.rmmcgabay.utils.Widget;

public class Hero extends AppCompatActivity {

    // Widgets
    private BottomNavigationView navigation;
    private NavHostFragment content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        navigation = findViewById(R.id.bnv_hero);
        content = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fcv_hero);

        if (content != null) {
            NavigationUI.setupWithNavController(navigation , content.getNavController());
        }
    }

}