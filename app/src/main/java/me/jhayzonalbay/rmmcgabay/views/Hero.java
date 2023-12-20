package me.jhayzonalbay.rmmcgabay.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.db.Database;
import me.jhayzonalbay.rmmcgabay.models.UserType;
import me.jhayzonalbay.rmmcgabay.utils.Gabay;
import me.jhayzonalbay.rmmcgabay.utils.Messenger;
import me.jhayzonalbay.rmmcgabay.utils.Widget;

public class Hero extends AppCompatActivity {

    private MaterialCardView order;
    private MaterialCardView transactions;
    private MaterialCardView account;
    private MaterialCardView manage;
    private Gabay gabay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        order = findViewById(R.id.cv_hero_order);
        transactions = findViewById(R.id.cv_hero_transactions);
        account = findViewById(R.id.cv_hero_account);
        manage = findViewById(R.id.cv_hero_manage);

        gabay = new Gabay(getSharedPreferences("gabay", MODE_PRIVATE));


        account.setOnClickListener(v -> {
            Intent intent = new Intent(this, Account.class);
            startActivity(intent);
        });

        order.setOnClickListener(v -> {
            Intent intent = new Intent(this, Order.class);
            startActivity(intent);
        });

        transactions.setOnClickListener(v -> {
            Intent intent = new Intent(this, History.class);
            startActivity(intent);
        });

        manage.setOnClickListener(v -> {

            if (!gabay.getBool("IS_ADMIN")) {
                Messenger.showAlertDialog(this, "Access Invalid", "You don't have permission to access this function!", "Ok").show();

                return;
            }
            Intent intent = new Intent(this, Manage.class);
            startActivity(intent);
        });

    }

}