package me.jhayzonalbay.rmmcgabay.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import me.jhayzonalbay.rmmcgabay.db.Database;
import me.jhayzonalbay.rmmcgabay.models.Invoice;

public class InvoiceRepository implements CrudRepository<Invoice> {

    private Context context;

    public InvoiceRepository(Context context) {
        this.context = context;
    }

    @Override
    public Invoice insert(Invoice invoice) {
        SQLiteDatabase db = Database.getWritableDatabase(context);
        ContentValues values = new ContentValues();

        values.put(Invoice.USER_ID, invoice.getUserId());
        values.put(Invoice.TRANSACTION_DATE, invoice.getTransactionDate().toString());
        values.put(Invoice.SUBTOTAL, invoice.getSubTotal());

        long transactionId = db.insert("Invoice", null, values);
        invoice.setId((int) transactionId);

        return invoice;
    }

    @Override
    public Invoice update(Invoice invoice) {
        SQLiteDatabase db = Database.getWritableDatabase(context);
        ContentValues values = new ContentValues();

        values.put(Invoice.USER_ID, invoice.getUserId());
        values.put(Invoice.TRANSACTION_DATE, invoice.getTransactionDate().toString());
        values.put(Invoice.SUBTOTAL, invoice.getSubTotal());

        String selection = Invoice.ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(invoice.getId()) };

        db.update("Invoice", values, selection, selectionArgs);

        return invoice;
    }

    @Override
    public Invoice delete(Invoice invoice) {
        SQLiteDatabase db = Database.getWritableDatabase(context);
        String selection = Invoice.ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(invoice.getId()) };

        db.delete("Invoice", selection, selectionArgs);

        return invoice;
    }

    // Get all invoices
    public List<Invoice> getAll() {
        SQLiteDatabase db = Database.getReadableDatabase(context);
        List<Invoice> invoices = new ArrayList<>();
        Cursor cursor = db.query(
                "Invoice",
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Invoice invoice = new Invoice();

            invoice.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Invoice.ID)));
            invoice.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(Invoice.USER_ID)));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                invoice.setTransactionDate(LocalDate.parse(cursor.getString(cursor.getColumnIndexOrThrow(Invoice.TRANSACTION_DATE))));
            }
            invoice.setSubTotal(cursor.getDouble(cursor.getColumnIndexOrThrow(Invoice.SUBTOTAL)));

            invoices.add(invoice);
            Log.d("RICHIE", String.format("INVOICE: %s", invoice));
        }

        return invoices;
    }
}
