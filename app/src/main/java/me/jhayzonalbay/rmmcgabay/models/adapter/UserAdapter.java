package me.jhayzonalbay.rmmcgabay.models.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.User;
import me.jhayzonalbay.rmmcgabay.utils.Item;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> users;
    private Item item;

    public UserAdapter(List<User> users, Item item) {
        this.users = users;
        this.item = item;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_user, parent, false);

        return new UserViewHolder(view, item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);

        holder.name.setText(user.getFullName());
        holder.role.setText(user.getUserType().getType());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView role;

        private Button edit;
        private Button delete;

        public UserViewHolder(@NonNull View view, Item item) {
            super(view);

            name = view.findViewById(R.id.tv_user_name);
            role = view.findViewById(R.id.tv_user_role);

            edit = view.findViewById(R.id.btn_user_edit);
            delete = view.findViewById(R.id.btn_user_delete);

            edit.setOnClickListener(v -> item.edit(getAdapterPosition()));
            delete.setOnClickListener(v -> item.delete(getAdapterPosition()));
        }
    }
}
