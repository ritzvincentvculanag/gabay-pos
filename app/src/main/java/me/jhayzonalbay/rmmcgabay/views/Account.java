package me.jhayzonalbay.rmmcgabay.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.User;
import me.jhayzonalbay.rmmcgabay.repositories.UserRepository;
import me.jhayzonalbay.rmmcgabay.utils.Gabay;

public class Account extends AppCompatActivity {

    private TextView fullName;
    private TextView role;
    private Button delete;
    private Button logout;

    private Gabay gabay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        fullName = findViewById(R.id.tv_account_name);
        role = findViewById(R.id.tv_account_role);
        delete = findViewById(R.id.btn_account_delete);
        logout = findViewById(R.id.btn_account_logout);

        gabay = new Gabay(this.getSharedPreferences("gabay", MODE_PRIVATE));
        fullName.setText(gabay.getString("USER_NAME"));
        role.setText(gabay.getString("USER_TYPE"));

        logout.setOnClickListener(v -> {
            gabay.clear();
            Intent goToLogin = new Intent(this, me.jhayzonalbay.rmmcgabay.views.Login.class);
            startActivity(goToLogin);
            finish();
        });

        delete.setOnClickListener(v -> {
            UserRepository userRepository = new UserRepository(this);
            userRepository.delete(userRepository.getUser(gabay.getInt("USER_ID")));
            gabay.clear();
            Intent goToLogin = new Intent(this, me.jhayzonalbay.rmmcgabay.views.Login.class);
            startActivity(goToLogin);
            finish();
        });
    }
}