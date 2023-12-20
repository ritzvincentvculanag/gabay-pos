package me.jhayzonalbay.rmmcgabay.repositories;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import me.jhayzonalbay.rmmcgabay.db.Database;

import me.jhayzonalbay.rmmcgabay.models.Product;
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
        values.put(PurchasedItem.QUANTITY, purchasedItem.getQuantity());

        long purchasedItemId = db.insert("PurchasedItem", null, values);
        updateQuantity(purchasedItem.getProductId(), purchasedItem.getQuantity());
        return purchasedItem;
    }

    public void updateQuantity(long productId, int quantity) {
        SQLiteDatabase db = Database.getWritableDatabase(context);
        ContentValues values = new ContentValues();
        String selection = Product.ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(productId)};

        int oldQuantity = getOldQuantity(productId);

        // Ensure the new quantity is not negative
        int newQuantity = oldQuantity - quantity;

        Log.d(TAG,"New Quan " + newQuantity);

        values.put(Product.QUANTITY, newQuantity);

        db.update("Product", values, selection, selectionArgs);
    }


    // Helper method to get the old quantity from the database
    private int getOldQuantity(long productId) {
        SQLiteDatabase db = Database.getReadableDatabase(context);
        String[] projection = {PurchasedItem.QUANTITY};
        String selection = Product.ID + " = ?";
        String[] selectionArgs = {String.valueOf(productId)};

        Cursor cursor = db.query("Product", projection, selection, selectionArgs, null, null, null);

        int oldQuantity = 0;

        if (cursor != null && cursor.moveToFirst()) {
            oldQuantity = cursor.getInt(cursor.getColumnIndexOrThrow(PurchasedItem.QUANTITY));
            cursor.close();
        }

        return oldQuantity;
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
