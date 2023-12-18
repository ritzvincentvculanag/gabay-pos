package me.jhayzonalbay.rmmcgabay.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import me.jhayzonalbay.rmmcgabay.db.Database;
import me.jhayzonalbay.rmmcgabay.models.Category;
import me.jhayzonalbay.rmmcgabay.models.Product;

public class ProductRepository implements CrudRepository<Product> {

    private Context context;

    public ProductRepository(Context context) {
        this.context = context;
    }

    @Override
    public Product insert(Product product) {
        SQLiteDatabase db = Database.getWritableDatabase(context);
        ContentValues values = new ContentValues();

        values.put(Product.NAME, product.getName());
        values.put(Product.DESCRIPTION, product.getDescription());
        values.put(Product.BARCODE, product.getBarcode());
        values.put(Product.PRICE, product.getPrice());
        values.put(Product.CATEGORY, product.getCategory().getId());

        long productId = db.insert("Product", null, values);
        product.setId((int) productId);

        Toast.makeText(context, "Product added!", Toast.LENGTH_SHORT).show();

        return product;
    }

    @Override
    public Product update(Product product) {
        SQLiteDatabase db = Database.getWritableDatabase(context);
        ContentValues values = new ContentValues();
        String selection = Product.ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(product.getId()) };

        values.put(Product.NAME, product.getName());
        values.put(Product.DESCRIPTION, product.getDescription());
        values.put(Product.BARCODE, product.getBarcode());
        values.put(Product.PRICE, product.getPrice());
        values.put(Product.CATEGORY, product.getCategory().getId());

        MaterialAlertDialogBuilder alert = new MaterialAlertDialogBuilder(context);

        alert.setTitle("Update product");
        alert.setMessage("Are you sure you want to update this product?");
        alert.setPositiveButton("Yes", ((dialog, which) -> {
            db.update("Product", values, selection, selectionArgs);
            Toast.makeText(context, product.getName() + " updated!", Toast.LENGTH_SHORT).show();
        }));
        alert.setNegativeButton("No", ((dialog, which) -> Toast.makeText(context, "Aborting", Toast.LENGTH_SHORT).show()));
        alert.show();

        Log.d("RITCHIE", product.toString());

        return product;
    }

    @Override
    public Product delete(Product product) {
        SQLiteDatabase db = Database.getWritableDatabase(context);
        String selection = Product.ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(product.getId()) };

        MaterialAlertDialogBuilder alert = new MaterialAlertDialogBuilder(context);

        alert.setTitle("Delete product");
        alert.setMessage("Are you sure you want to delete this product?");
        alert.setPositiveButton("Yes", ((dialog, which) -> {
            db.delete("Product", selection, selectionArgs);
            Toast.makeText(context, product.getName() + " deleted!", Toast.LENGTH_SHORT).show();
        }));
        alert.setNegativeButton("No", ((dialog, which) -> Toast.makeText(context, "Aborting", Toast.LENGTH_SHORT).show()));
        alert.show();

        return product;
    }

    @Override
    public List<Product> getAll() {
        CategoryRepository categoryRepository = new CategoryRepository(context);
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = Database.getReadableDatabase(context);
        Cursor cursor = db.query(
                "Product",
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(Product.ID));
            long categoryId = cursor.getLong(cursor.getColumnIndexOrThrow(Product.CATEGORY));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(Product.NAME));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(Product.DESCRIPTION));
            String barcode = cursor.getString(cursor.getColumnIndexOrThrow(Product.BARCODE));
            double price = cursor.getDouble(cursor.getColumnIndexOrThrow(Product.PRICE));

            Category category = categoryRepository.getCategory((int) categoryId);
            Product product = new Product(name, description, barcode, price, category);
            product.setId((int) id);
            products.add(product);
        }

        return products;
    }
}
