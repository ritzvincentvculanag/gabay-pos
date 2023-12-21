package me.jhayzonalbay.rmmcgabay.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.User;
import me.jhayzonalbay.rmmcgabay.repositories.UserRepository;
import me.jhayzonalbay.rmmcgabay.utils.Gabay;
import me.jhayzonalbay.rmmcgabay.utils.Messenger;
import me.jhayzonalbay.rmmcgabay.views.edit.EditUser;

public class Account extends AppCompatActivity {

    private TextView fullName;
    private TextView role;
    private Button delete;
    private Button logout;
    private Button update;

    private Gabay gabay;
    private  UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        userRepository = new UserRepository(this);

        fullName = findViewById(R.id.tv_account_name);
        role = findViewById(R.id.tv_account_role);
        delete = findViewById(R.id.btn_account_delete);
        logout = findViewById(R.id.btn_account_logout);
        update = findViewById(R.id.btn_account_change);

        gabay = new Gabay(this.getSharedPreferences("gabay", MODE_PRIVATE));
        fullName.setText(gabay.getString("USER_NAME"));
        role.setText(gabay.getString("USER_ROLE"));
        Context context = this;

        update.setOnClickListener(e -> {

            User user = userRepository.getUser(gabay.getInt("USER_ID"));
            Intent intent = new Intent(this, EditUser.class);
            intent.putExtra("EXT_USER", user);
            startActivity(intent);
        });

        logout.setOnClickListener(v -> {

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

        delete.setOnClickListener(v -> {

            Messenger.showAlertDialog(this, "Delete Account", "Do you want to delete this account?", "Yes", "No",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            UserRepository userRepository = new UserRepository(context);
                            userRepository.delete(userRepository.getUser(gabay.getInt("USER_ID")));
                            gabay.clear();
                            Intent goToLogin = new Intent(context, me.jhayzonalbay.rmmcgabay.views.Login.class);
                            startActivity(goToLogin);
                            finish();
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();

        });
    }
}