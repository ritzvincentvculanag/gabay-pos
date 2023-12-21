package me.jhayzonalbay.rmmcgabay.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.db.Database;
import me.jhayzonalbay.rmmcgabay.models.User;
import me.jhayzonalbay.rmmcgabay.models.UserType;
import me.jhayzonalbay.rmmcgabay.repositories.UserRepository;
import me.jhayzonalbay.rmmcgabay.repositories.UserTypeRepository;
import me.jhayzonalbay.rmmcgabay.utils.Gabay;
import me.jhayzonalbay.rmmcgabay.utils.Miner;
import me.jhayzonalbay.rmmcgabay.utils.Widget;

public class Login extends AppCompatActivity implements Widget {

    // Widgets
    private TextInputLayout username;
    private TextInputLayout password;

    private Button login;

    private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;
    private Gabay gabay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Database.getWritableDatabase(this);

        userRepository = new UserRepository(this);
        gabay = new Gabay(getSharedPreferences("gabay", MODE_PRIVATE));


        if (gabay.getBool("IS_LOGGED_IN")) {
            Intent goToDashboard = new Intent(this, Hero.class);
            startActivity(goToDashboard);
            finish();
        }
        initPermission();

        initWidgets();
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ContextCompat.checkSelfPermission(Login.this,
                    Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Login.this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }
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
            User user = userRepository.getUser(username, password);
            gabay.save("USER_ID", user.getId());
            gabay.save("USER_NAME", user.getFullName());
            gabay.save("USER_ROLE", user.getUserType().getType());
            gabay.save("IS_ADMIN", user.getUserType().getId() == 1);
            gabay.save("IS_LOGGED_IN", true);


            Intent goToDashboard = new Intent(this, Hero.class);
            startActivity(goToDashboard);
            finish();

        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

}