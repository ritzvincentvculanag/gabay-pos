package me.jhayzonalbay.rmmcgabay.views.manage;

import android.content.Intent;
import android.hardware.lights.LightState;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.User;
import me.jhayzonalbay.rmmcgabay.models.adapter.UserAdapter;
import me.jhayzonalbay.rmmcgabay.repositories.UserRepository;
import me.jhayzonalbay.rmmcgabay.utils.Item;
import me.jhayzonalbay.rmmcgabay.views.edit.EditUser;

public class ManageUser extends Fragment implements Item {

    private View view;

    private FloatingActionButton addUser;

    private List<User> users;
    private UserAdapter userAdapter;
    private UserRepository userRepository;
    private RecyclerView usersView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_manage_user, container, false);

        addUser = view.findViewById(R.id.fab_user_add);
        addUser.setOnClickListener(v -> startActivity(new Intent(getContext(), EditUser.class)));

        userRepository = new UserRepository(getContext());
        users = userRepository.getAll();
        userAdapter = new UserAdapter(users, this);
        usersView = view.findViewById(R.id.rv_users_list);
        usersView.setAdapter(userAdapter);
        usersView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void edit(int position) {
        User user = users.get(position);
        Intent goToEditUser = new Intent(getContext(), EditUser.class);
        goToEditUser.putExtra("EXT_USER", user);
        startActivity(goToEditUser);
    }

    @Override
    public void delete(int position) {
        User user = users.get(position);
        userRepository.delete(user);
    }
}