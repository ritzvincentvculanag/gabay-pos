package me.jhayzonalbay.rmmcgabay.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import me.jhayzonalbay.rmmcgabay.db.Database;
import me.jhayzonalbay.rmmcgabay.models.PurchasedItem;

public class TransactionRepository implements CrudRepository<PurchasedItem> {

    private Context context;

    public TransactionRepository(Context context) {
        this.context = context;
    }

    @Override
    public PurchasedItem insert(PurchasedItem purchasedItem) {
        SQLiteDatabase db = Database.getWritableDatabase(context);
        ContentValues values = new ContentValues();

        values.put(PurchasedItem.PRODUCT_ID, purchasedItem.getProductId());
        values.put(PurchasedItem.TRANSACTION_ID, purchasedItem.getTransactionId());

        long purchasedItemId = db.insert("PurchasedItem", null, values);

        return purchasedItem;
    }

    @Override
    public PurchasedItem update(PurchasedItem purchasedItem) {
        return null;
    }

    @Override
    public PurchasedItem delete(PurchasedItem purchasedItem) {
        return null;
    }

    @Override
    public List<PurchasedItem> getAll() {
        return null;
    }
}
