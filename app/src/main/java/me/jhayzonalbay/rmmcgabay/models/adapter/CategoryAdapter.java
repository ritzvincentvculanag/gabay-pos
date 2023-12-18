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
import me.jhayzonalbay.rmmcgabay.models.Category;
import me.jhayzonalbay.rmmcgabay.utils.Item;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private List<Category> categories;
    private Item categoryItem;

    public CategoryAdapter(List<Category> categories, Item categoryItem) {
        this.categories = categories;
        this.categoryItem = categoryItem;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_category, parent, false);

        return new CategoryHolder(view, categoryItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category category = categories.get(position);

        holder.name.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryHolder extends RecyclerView.ViewHolder {
        private TextView name;

        private Button edit;
        private Button delete;

        public CategoryHolder(@NonNull View view, Item categoryItem) {
            super(view);

            name = view.findViewById(R.id.tv_cat_name);

            edit = view.findViewById(R.id.btn_cat_edit);
            delete = view.findViewById(R.id.btn_category_delete);

            edit.setOnClickListener(e -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    categoryItem.edit(getAdapterPosition());
                }
            });
            delete.setOnClickListener(e -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    categoryItem.delete(getAdapterPosition());
                }
            });
        }
    }

}
