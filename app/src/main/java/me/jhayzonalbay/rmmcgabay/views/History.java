package me.jhayzonalbay.rmmcgabay.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.Invoice;
import me.jhayzonalbay.rmmcgabay.models.adapter.InvoiceAdapter;
import me.jhayzonalbay.rmmcgabay.repositories.InvoiceRepository;
import me.jhayzonalbay.rmmcgabay.repositories.TransactionRepository;
import me.jhayzonalbay.rmmcgabay.repositories.UserRepository;

public class History extends AppCompatActivity {

    private InvoiceRepository invoiceRepository;
    private List<Invoice> invoices;
    private InvoiceAdapter invoiceAdapter;
    private RecyclerView transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        invoiceRepository = new InvoiceRepository(this);
        invoices = invoiceRepository.getAll();
        invoiceAdapter = new InvoiceAdapter(new UserRepository(this), invoices);
        transactions = findViewById(R.id.rv_history_transactions);
        transactions.setAdapter(invoiceAdapter);
        transactions.setLayoutManager(new LinearLayoutManager(this));

        Log.d("RICHIE", String.format("INVOICES: %s", invoices) );
    }
}