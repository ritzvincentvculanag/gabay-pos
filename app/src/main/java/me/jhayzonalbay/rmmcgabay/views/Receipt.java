package me.jhayzonalbay.rmmcgabay.views;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.Invoice;
import me.jhayzonalbay.rmmcgabay.models.PurchasedItem;
import me.jhayzonalbay.rmmcgabay.models.adapter.PurchasedItemAdapter;
import me.jhayzonalbay.rmmcgabay.repositories.ProductRepository;
import me.jhayzonalbay.rmmcgabay.repositories.TransactionRepository;

public class Receipt extends AppCompatActivity {

    private TransactionRepository transactionRepository;
    private List<PurchasedItem> purchasedItems;
    private PurchasedItemAdapter purchasedItemAdapter;
    private RecyclerView items;
    private Invoice invoice;

    private TextView total;
    private TextView id;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        invoice = getIntent().getExtras().getParcelable("EXT_INVOICE");

        total = findViewById(R.id.tv_receipt_total);
        id = findViewById(R.id.tv_receipt_id);
        date = findViewById(R.id.tv_receipt_date);

        total.setText(invoice.getSubTotal().toString());
        id.setText(String.valueOf(invoice.getId()));

        if (invoice.getTransactionDate() != null) {
            date.setText(String.format("%s", invoice.getTransactionDate().toString()));
        }

        transactionRepository = new TransactionRepository(this);
        purchasedItems = transactionRepository.getAll(invoice.getId());
        purchasedItemAdapter = new PurchasedItemAdapter(purchasedItems, new ProductRepository(this));
        items = findViewById(R.id.rv_receipt_items);
        items.setAdapter(purchasedItemAdapter);
        items.setLayoutManager(new LinearLayoutManager(this));

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(Receipt.this, "Thank you for purchasing!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}