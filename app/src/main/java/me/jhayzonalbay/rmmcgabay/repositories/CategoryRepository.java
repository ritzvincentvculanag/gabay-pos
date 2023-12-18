package me.jhayzonalbay.rmmcgabay.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.jhayzonalbay.rmmcgabay.db.Database;
import me.jhayzonalbay.rmmcgabay.models.Category;

public class CategoryRepository implements CrudRepository<Category> {

    private final Context context;

    public CategoryRepository(Context context) {
        this.context = context;
    }

    @Override
    public Category insert(Category category) {
        SQLiteDatabase db = Database.getWritableDatabase(context);
        ContentValues values = new ContentValues();

        values.put(Category.NAME, category.getName());
        long categoryId = db.insert("Category", null, values);
        category.setId((int) categoryId);

        Toast.makeText(context, "Category added!", Toast.LENGTH_SHORT).show();

        return category;
    }

    @Override
    public Category update(Category category) {
        SQLiteDatabase db = Database.getWritableDatabase(context);
        ContentValues values = new ContentValues();
        String selection = Category.ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(category.getId()) };

        values.put(Category.NAME, category.getName());
        db.update("Category", values, selection, selectionArgs);

        return null;
    }

    @Override
    public Category delete(Category category) {
        SQLiteDatabase db = Database.getWritableDatabase(context);
        String selection = Category.ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(category.getId()) };

        db.delete("Category", selection, selectionArgs);

        return category;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        SQLiteDatabase db = Database.getReadableDatabase(context);
        String[] projection = { Category.ID, Category.NAME };
        Cursor cursor = db.query(
                "Category",
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(Category.ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(Category.NAME));
            Category category = new Category((int) id, name);

            categories.add(category);
        }

        cursor.close();

        return categories;
    }
}
