package me.jhayzonalbay.rmmcgabay.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.repositories.UserRepository;
import me.jhayzonalbay.rmmcgabay.utils.Miner;
import me.jhayzonalbay.rmmcgabay.utils.Widget;

public class Login extends AppCompatActivity implements Widget {

    // Widgets
    private TextInputLayout username;
    private TextInputLayout password;

    private Button login;

    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userRepository = new UserRepository(this);

        initWidgets();
    }

    @Override
    public void initWidgets() {
        username = findViewById(R.id.til_login_username);
        password = findViewById(R.id.til_login_password);

        login = findViewById(R.id.btn_login_signin);
        login.setOnClickListener(this::loginAction);
    }

    private void loginAction(View view) {
        String username = Miner.getContent(this.username);
        String password = Miner.getContent(this.password);

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill up all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userRepository.getUser(username, password).getId() > 0) {
            Intent goToDashboard = new Intent(this, Hero.class);
            startActivity(goToDashboard);
            finish();
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}