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

public class ProductCheckoutAdapter extends RecyclerView.Adapter<ProductCheckoutAdapter.ProductCheckoutHolder> {

    private List<Product> products;
    private Item item;

    public ProductCheckoutAdapter(List<Product> products, Item item) {
        this.products = products;
        this.item = item;
    }

    @NonNull
    @Override
    public ProductCheckoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_checkout, parent, false);

        return new ProductCheckoutHolder(view, item);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCheckoutHolder holder, int position) {
        Product product = products.get(position);
        Double price = product.getQuantity() * product.getPrice();

        holder.name.setText(product.getName());
        holder.description.setText(product.getDescription());
        holder.price.setText(String.format(Locale.US, "P%.2f", price));
        holder.quantity.setText(String.format(Locale.US, "%d", product.getQuantity()));

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

    public void update(List<Product> newProducts) {
        this.products = newProducts;
        notifyDataSetChanged();
    }

    public static class ProductCheckoutHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView description;
        private TextView price;
        private TextView quantity;

        private Button increase;
        private Button decrease;
        private Button remove;

        public ProductCheckoutHolder(@NonNull View view, Item item) {
            super(view);

            name = view.findViewById(R.id.tv_checkout_name);
            description = view.findViewById(R.id.tv_checkout_description);
            price = view.findViewById(R.id.tv_checkout_subtotal);
            quantity = view.findViewById(R.id.tv_checkout_quantity);

            increase = view.findViewById(R.id.btn_checkout_increase);
            decrease = view.findViewById(R.id.btn_checkout_decrease);
            remove = view.findViewById(R.id.btn_checkout_remove);

            remove.setOnClickListener(e -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    item.delete(getAdapterPosition());
                }
            });
        }
    }

}
