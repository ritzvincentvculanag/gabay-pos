package me.jhayzonalbay.rmmcgabay.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.jhayzonalbay.rmmcgabay.db.Database;
import me.jhayzonalbay.rmmcgabay.models.Category;
import me.jhayzonalbay.rmmcgabay.models.Invoice;
import me.jhayzonalbay.rmmcgabay.models.Product;
import me.jhayzonalbay.rmmcgabay.models.PurchasedItem;

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

    @Override
    public List<Invoice> getAll() {

        List<Invoice> products = new ArrayList<>();
        SQLiteDatabase db = Database.getReadableDatabase(context);
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
            Integer id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            Integer user_id = cursor.getInt(cursor.getColumnIndexOrThrow(Invoice.USER_ID));
            long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow(Invoice.TRANSACTION_DATE));

            // Convert long to Date
            Instant instant = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                instant = Instant.ofEpochMilli(dateLong);
            }
            LocalDate localDate = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            }


            Double subtotal = cursor.getDouble(cursor.getColumnIndexOrThrow(Invoice.SUBTOTAL));

            products.add(new Invoice(null, localDate, subtotal));
        }
        return products;
    }
}
