package me.jhayzonalbay.rmmcgabay.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.util.List;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.Invoice;
import me.jhayzonalbay.rmmcgabay.models.Product;
import me.jhayzonalbay.rmmcgabay.models.PurchasedItem;
import me.jhayzonalbay.rmmcgabay.repositories.InvoiceRepository;
import me.jhayzonalbay.rmmcgabay.repositories.TransactionRepository;

public class Payment extends AppCompatActivity {

    private TextView total;

    private TextInputLayout amount;

    private Button pay;

    private Invoice invoice;

    private InvoiceRepository invoiceRepository;
    private TransactionRepository transactionRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        total = findViewById(R.id.tv_payment_total);

        amount = findViewById(R.id.til_payment_amount);

        pay = findViewById(R.id.btn_payment_pay);
        pay.setOnClickListener(this::payAction);

        invoiceRepository = new InvoiceRepository(this);
        transactionRepository = new TransactionRepository(this);

        invoice = getIntent().getExtras().getParcelable("EXT_INVOICE");

        total.setText(invoice.getSubTotal().toString());
    }

    private void payAction(View view) {
        Double amount = Double.parseDouble(this.amount.getEditText().getText().toString());

        if (amount < invoice.getSubTotal()) {
            Toast.makeText(this, "Insufficient amount", Toast.LENGTH_SHORT).show();
            return;
        }

        if (amount < 0) {
            Toast.makeText(this, "Invalid amount", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent goToHero = new Intent(this, Hero.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            invoice.setTransactionDate(LocalDate.now());
        }
        Invoice newInvoice = invoiceRepository.insert(invoice);
        newInvoice.getProducts().forEach(product -> {
            PurchasedItem purchasedItem = new PurchasedItem();
            purchasedItem.setProductId(product.getId());
            purchasedItem.setTransactionId(newInvoice.getId());
            purchasedItem.setQuantity(product.getQuantity());

            transactionRepository.insert(purchasedItem);
        });

        startActivity(goToHero);
        finish();
    }


}