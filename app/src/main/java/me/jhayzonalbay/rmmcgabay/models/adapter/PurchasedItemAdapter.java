package me.jhayzonalbay.rmmcgabay.models.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.Product;
import me.jhayzonalbay.rmmcgabay.models.PurchasedItem;
import me.jhayzonalbay.rmmcgabay.repositories.ProductRepository;

public class PurchasedItemAdapter extends RecyclerView.Adapter<PurchasedItemAdapter.PurchasedItemHolder>{

    private List<PurchasedItem> purchasedItems;
    private ProductRepository productRepository;

    public PurchasedItemAdapter(List<PurchasedItem> purchasedItems, ProductRepository productRepository) {
        this.purchasedItems = purchasedItems;
        this.productRepository = productRepository;
    }

    @NonNull
    @Override
    public PurchasedItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_receipt, parent, false);

        return new PurchasedItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasedItemHolder holder, int position) {
        PurchasedItem purchasedItem = purchasedItems.get(position);
        Product product = productRepository.getAll().stream().filter(p -> p.getId() == purchasedItem.getProductId()).findFirst().get();
        Double price = purchasedItem.getQuantity() * product.getPrice();

        holder.product.setText(product.getName());
        holder.quantity.setText(String.valueOf(purchasedItem.getQuantity()));
        holder.price.setText(String.valueOf(price));
    }

    @Override
    public int getItemCount() {
        return purchasedItems.size();
    }

    public static class PurchasedItemHolder extends RecyclerView.ViewHolder {

        private TextView product;
        private TextView quantity;
        private TextView price;

        public PurchasedItemHolder(@NonNull View itemView) {
            super(itemView);

            product = itemView.findViewById(R.id.tv_receipt_product);
            quantity = itemView.findViewById(R.id.tv_receipt_quantity);
            price = itemView.findViewById(R.id.tv_receipt_price);
        }
    }

}
