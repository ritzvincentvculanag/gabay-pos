package me.jhayzonalbay.rmmcgabay.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
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
    private MaterialCardView logout;
    private Gabay gabay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        Context context = this;

        order = findViewById(R.id.cv_hero_order);
        transactions = findViewById(R.id.cv_hero_transactions);
        account = findViewById(R.id.cv_hero_account);
        manage = findViewById(R.id.cv_hero_manage);
        logout = findViewById(R.id.cv_hero_logout);

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

        logout.setOnClickListener(e ->{
            Messenger.showAlertDialog(this, "Logout", "Do you want to logout", "Yes", "No",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            gabay.clear();
                            Intent goToLogin = new Intent(context, Login.class);
                            startActivity(goToLogin);
                            finish();
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();

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