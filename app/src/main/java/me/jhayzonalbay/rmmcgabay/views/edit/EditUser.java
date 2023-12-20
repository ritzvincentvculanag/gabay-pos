package me.jhayzonalbay.rmmcgabay.views.edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.stream.Collectors;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.User;
import me.jhayzonalbay.rmmcgabay.models.UserType;
import me.jhayzonalbay.rmmcgabay.repositories.UserRepository;
import me.jhayzonalbay.rmmcgabay.repositories.UserTypeRepository;
import me.jhayzonalbay.rmmcgabay.utils.Item;
import me.jhayzonalbay.rmmcgabay.utils.Miner;
import me.jhayzonalbay.rmmcgabay.utils.Validator;
import me.jhayzonalbay.rmmcgabay.utils.Widget;

public class EditUser extends AppCompatActivity implements Widget {

    private TextInputLayout firstName;
    private TextInputLayout lastName;
    private TextInputLayout middleName;
    private TextInputLayout username;
    private TextInputLayout password;
    private TextInputLayout passwordConfirm;

    private AutoCompleteTextView userType;

    private Button proceed;

    private UserTypeRepository userTypeRepository;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_users);

        initWidgets();
        setupForm();
        setupUserType();
    }

    @Override
    public void initWidgets() {
        firstName = findViewById(R.id.til_user_fname);
        lastName = findViewById(R.id.til_user_lname);
        middleName = findViewById(R.id.til_user_mname);
        username = findViewById(R.id.til_user_username);
        password = findViewById(R.id.til_user_password);
        passwordConfirm = findViewById(R.id.til_user_password_confirm);

        userType = findViewById(R.id.act_user_type);

        proceed = findViewById(R.id.btn_user_add);
        proceed.setOnClickListener(this::proceedAddUser);

        userTypeRepository = new UserTypeRepository(this);
        userRepository = new UserRepository(this);
    }

    private void setupForm() {
        if (getIntent().getExtras() == null) {
            return;
        }

        User user = getIntent().getExtras().getParcelable("EXT_USER");

        firstName.getEditText().setText(user.getFirstName());
        lastName.getEditText().setText(user.getLastName());
        middleName.getEditText().setText(user.getMiddleName());
        username.getEditText().setText(user.getUsername());
        password.getEditText().setText(user.getPassword());
        passwordConfirm.getEditText().setText(user.getPassword());
       // userType.setText(user.getUserType().getType());

        proceed.setText("Update");
        proceed.setOnClickListener(this::proceedUpdateUser);
    }

    private void setupUserType() {
        List<String> userTypes = userTypeRepository.getAll().stream()
                .map(UserType::getType)
                .collect(Collectors.toList());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, userTypes);
        userType.setAdapter(adapter);
    }

    private void proceedAddUser(View view) {
        if (!Validator.fieldsAreValid(
                firstName,
                lastName,
                username,
                password,
                passwordConfirm
        )) {
            return;
        }

        if (userType.getText().toString().isEmpty()) {
            Toast.makeText(this, "User type is required!", Toast.LENGTH_SHORT).show();

            return;
        }

        if (Miner.getContent(username).trim().length() < 4) {
            Toast.makeText(this, "Username must be at least 4 characters long.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Miner.getContent(password).trim().length() < 8) {
            Toast.makeText(this, "Password must be at least 8 characters long.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Miner.getContent(password).equals(Miner.getContent(passwordConfirm))) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        String fName = Miner.getContent(firstName);
        String lName = Miner.getContent(lastName);
        String mName = Miner.getContent(middleName);
        String uName = Miner.getContent(username);
        String pWord = Miner.getContent(password);
        UserType uType = userTypeRepository.getUserType(userType.getText().toString());

        User user = new User();
        user.setFirstName(fName);
        user.setLastName(lName);
        user.setMiddleName(mName);
        user.setUsername(uName);
        user.setPassword(pWord);
        user.setUserType(uType);

        Toast.makeText(this, "User added successfully!", Toast.LENGTH_SHORT).show();
        userRepository.insert(user);
    }

    public void proceedUpdateUser(View view) {
        if (!Validator.fieldsAreValid(
                firstName,
                lastName,
                username,
                password,
                passwordConfirm
        )) {
            return;
        }

        if (userType.getText().toString().isEmpty()) {
            Toast.makeText(this, "User type is required!", Toast.LENGTH_SHORT).show();

            return;
        }

        if (Miner.getContent(username).trim().length() < 4) {
            Toast.makeText(this, "Username must be at least 4 characters long.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Miner.getContent(password).trim().length() < 8) {
            Toast.makeText(this, "Password must be at least 8 characters long.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Miner.getContent(password).equals(Miner.getContent(passwordConfirm))) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        String fName = Miner.getContent(firstName);
        String lName = Miner.getContent(lastName);
        String mName = Miner.getContent(middleName);
        String uName = Miner.getContent(username);
        String pWord = Miner.getContent(password);
        UserType uType = userTypeRepository.getUserType(userType.getText().toString());

        User user = getIntent().getExtras().getParcelable("EXT_USER");
        user.setFirstName(fName);
        user.setLastName(lName);
        user.setMiddleName(mName);
        user.setUsername(uName);
        user.setPassword(pWord);
        user.setUserType(uType);

        userRepository.update(user);
    }

}