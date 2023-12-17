package me.jhayzonalbay.rmmcgabay.models.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.Product;
import me.jhayzonalbay.rmmcgabay.repositories.CrudRepository;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private final CrudRepository<Product> repository;

    public ProductAdapter(CrudRepository<Product> repository) {
        this.repository = repository;
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
        Product product = repository.getAll().get(position);

        holder.name.setText(product.getName());
        holder.description.setText(product.getDescription());
        holder.price.setText(String.format(Locale.US, "%f.00", product.getPrice()));

    }

    @Override
    public int getItemCount() {
        return repository.getAll().size();
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

            // TODO: implement button clicks
        }
    }

}
