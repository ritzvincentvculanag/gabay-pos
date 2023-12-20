package me.jhayzonalbay.rmmcgabay.models.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.Invoice;
import me.jhayzonalbay.rmmcgabay.models.User;
import me.jhayzonalbay.rmmcgabay.repositories.UserRepository;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceHolder> {

    private UserRepository userRepository;
    private List<Invoice> invoices;

    public InvoiceAdapter(UserRepository userRepository, List<Invoice> invoices) {
        this.userRepository = userRepository;
        this.invoices = invoices;
    }

    @NonNull
    @Override
    public InvoiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_invoice, parent, false);

        return new InvoiceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceHolder holder, int position) {
        Invoice invoice = invoices.get(position);
        User user = userRepository.getUser(invoice.getUserId());

        if (user != null) {
            holder.clerk.setText(String.format("%s", user.getUsername()));
        } else {
            holder.clerk.setText("Unkown");
        }

        holder.id.setText(String.format("%d", invoice.getId()));
        holder.date.setText(String.format("%s", invoice.getTransactionDate().toString()));
        holder.subtotal.setText(String.format("P%.2f", invoice.getSubTotal()));
    }

    @Override
    public int getItemCount() {
        return invoices.size();
    }

    public static class InvoiceHolder extends RecyclerView.ViewHolder {

        private TextView id;
        private TextView clerk;
        private TextView date;
        private TextView subtotal;

        public InvoiceHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.tv_trans_id);
            clerk = itemView.findViewById(R.id.tv_trans_clerk);
            date = itemView.findViewById(R.id.tv_trans_date);
            subtotal = itemView.findViewById(R.id.tv_trans_amount);
        }
    }

}
