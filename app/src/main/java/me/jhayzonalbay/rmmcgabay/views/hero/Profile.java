package me.jhayzonalbay.rmmcgabay.views.hero;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.User;
import me.jhayzonalbay.rmmcgabay.repositories.UserRepository;
import me.jhayzonalbay.rmmcgabay.utils.Gabay;
import me.jhayzonalbay.rmmcgabay.utils.Widget;
import me.jhayzonalbay.rmmcgabay.views.Manage;
import me.jhayzonalbay.rmmcgabay.views.edit.EditUser;

public class Profile extends Fragment implements Widget {

    private View view;

    private TextView fullName;
    private TextView role;

    private Button manageSystem;
    private Button editProfile;
    private Button logout;

    private Gabay gabay;
    private UserRepository userRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        userRepository = new UserRepository(getContext());
        initWidgets();

        return view;
    }

    @Override
    public void initWidgets() {
        manageSystem = view.findViewById(R.id.btn_profile_manage);
        manageSystem.setOnClickListener(this::manageSystemAction);

        fullName = view.findViewById(R.id.tv_profile_name);
        role = view.findViewById(R.id.tv_profile_role);
        editProfile = view.findViewById(R.id.btn_profile_edit);
        editProfile.setOnClickListener(this::editProfile);
        logout = view.findViewById(R.id.btn_profile_logout);
        logout.setOnClickListener(v -> {
            gabay.clear();
            Intent goToLogin = new Intent(getContext(), me.jhayzonalbay.rmmcgabay.views.Login.class);
            startActivity(goToLogin);
            getActivity().finish();
        });

        gabay = new Gabay(getContext().getSharedPreferences("gabay", getContext().MODE_PRIVATE));
        fullName.setText(gabay.getString("USER_NAME"));
        role.setText(gabay.getString("USER_ROLE"));

        editProfile.setVisibility(gabay.getBool("IS_ADMIN") ? View.VISIBLE : View.GONE);
        manageSystem.setVisibility(gabay.getBool("IS_ADMIN") ? View.VISIBLE : View.GONE);

    }

    private void editProfile(View view) {
        User user = userRepository.getUser(gabay.getInt("USER_ID"));
        Intent intent = new Intent(getContext(), EditUser.class);
        intent.putExtra("EXT_USER", user);
        startActivity(intent);

    }

    private void manageSystemAction(View view) {
        Intent goToManage = new Intent(getContext(), Manage.class);
        startActivity(goToManage);
    }
}