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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private final List<Product> products;

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_product, parent, false);

        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product = products.get(position);

        holder.name.setText(product.getName());
        holder.description.setText(product.getDescription());
        holder.price.setText(String.format(Locale.US, "P%.2f", product.getPrice()));

        holder.increase.setOnClickListener(e -> {
            product.increase();
            holder.quantity.setText(String.format(Locale.US, "%d", product.getQuantity()));
        });

        holder.decrease.setOnClickListener(e -> {
            product.decrease();
            holder.quantity.setText(String.format(Locale.US, "%d", product.getQuantity()));
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView description;
        private TextView price;
        private TextView quantity;

        private Button increase;
        private Button decrease;
        private Button add;

        public ProductHolder(@NonNull View view) {
            super(view);

            name = view.findViewById(R.id.tv_product_name);
            description = view.findViewById(R.id.tv_product_description);
            price = view.findViewById(R.id.tv_product_price);
            quantity = view.findViewById(R.id.tv_product_quantity);

            increase = view.findViewById(R.id.btn_product_increase);
            decrease = view.findViewById(R.id.btn_product_decrease);
            add = view.findViewById(R.id.btn_product_add);
        }
    }

}
