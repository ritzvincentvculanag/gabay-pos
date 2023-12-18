package me.jhayzonalbay.rmmcgabay.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

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
        return null;
    }

    @Override
    public Category delete(Category category) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        return null;
    }
}
