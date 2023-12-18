package me.jhayzonalbay.rmmcgabay.models.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.Product;
import me.jhayzonalbay.rmmcgabay.utils.Item;

public class ManageProductAdapter extends RecyclerView.Adapter<ManageProductAdapter.ManageProductHolder>{

    private List<Product> products;
    private Item item;

    public ManageProductAdapter(List<Product> products, Item item) {
        this.products = products;
        this.item = item;
    }

    @NonNull
    @Override
    public ManageProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_product_manage, parent, false);

        return new ManageProductHolder(view, item);
    }

    @Override
    public void onBindViewHolder(@NonNull ManageProductHolder holder, int position) {
        Product product = products.get(position);

        holder.name.setText(product.getName());
        holder.description.setText(product.getDescription());
        holder.price.setText(String.format(Locale.US, "P%.2f", product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ManageProductHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView description;
        private TextView price;

        private Button edit;
        private Button delete;

        public ManageProductHolder(@NonNull View view, Item item) {
            super(view);

            name = view.findViewById(R.id.tv_mproduct_name);
            description = view.findViewById(R.id.tv_mproduct_description);
            price = view.findViewById(R.id.tv_mproduct_price);

            edit = view.findViewById(R.id.btn_mproduct_edit);
            delete = view.findViewById(R.id.btn_mproduct_delete);

            edit.setOnClickListener(e -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    item.edit(getAdapterPosition());
                }
            });
            delete.setOnClickListener(e -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    item.delete(getAdapterPosition());
                }
            });
        }
    }

}
