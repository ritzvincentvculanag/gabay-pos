package me.jhayzonalbay.rmmcgabay.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.utils.Widget;

public class Login extends AppCompatActivity implements Widget {

    // Widgets
    private TextInputLayout username;
    private TextInputLayout password;

    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initWidgets();
    }

    @Override
    public void initWidgets() {
        username = findViewById(R.id.til_login_username);
        password = findViewById(R.id.til_login_password);

        login = findViewById(R.id.btn_login_signin);
    }
}