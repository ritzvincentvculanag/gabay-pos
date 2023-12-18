package me.jhayzonalbay.rmmcgabay.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import me.jhayzonalbay.rmmcgabay.db.Database;
import me.jhayzonalbay.rmmcgabay.models.User;
import me.jhayzonalbay.rmmcgabay.models.UserType;

public class UserRepository implements CrudRepository<User> {

    private Context context;

    public UserRepository(Context context) {
        this.context = context;
    }

    @Override
    public User insert(User user) {
        SQLiteDatabase db = Database.getWritableDatabase(context);
        ContentValues values = new ContentValues();

        values.put(User.FIRST_NAME, user.getFirstName());
        values.put(User.LAST_NAME, user.getLastName());
        values.put(User.MIDDLE_NAME, user.getMiddleName());
        values.put(User.USERNAME, user.getUsername());
        values.put(User.PASSWORD, user.getPassword());
        values.put(User.USER_TYPE, user.getUserType().getId());

        long userId = db.insert("User", null, values);
        user.setId((int) userId);

        return user;
    }

    @Override
    public User update(User user) {
        SQLiteDatabase db = Database.getWritableDatabase(context);
        ContentValues values = new ContentValues();
        String selection = User.ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(user.getId()) };

        values.put(User.FIRST_NAME, user.getFirstName());
        values.put(User.LAST_NAME, user.getLastName());
        values.put(User.MIDDLE_NAME, user.getMiddleName());
        values.put(User.USERNAME, user.getUsername());
        values.put(User.PASSWORD, user.getPassword());
        values.put(User.USER_TYPE, user.getUserType().getId());

        MaterialAlertDialogBuilder alert = new MaterialAlertDialogBuilder(context);

        alert.setTitle("Update user");
        alert.setMessage("Are you sure you want to update this user?");
        alert.setPositiveButton("Yes", ((dialog, which) -> {
            db.update("User", values, selection, selectionArgs);
            Toast.makeText(context, user.getFirstName() + " updated!", Toast.LENGTH_SHORT).show();
        }));
        alert.setNegativeButton("No", ((dialog, which) -> Toast.makeText(context, "Aborting", Toast.LENGTH_SHORT).show()));
        alert.show();

        return user;
    }

    @Override
    public User delete(User user) {
        SQLiteDatabase db = Database.getWritableDatabase(context);
        String selection = User.ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(user.getId()) };

        MaterialAlertDialogBuilder alert = new MaterialAlertDialogBuilder(context);

        alert.setTitle("Delete user?");
        alert.setMessage("Are you sure you want to delete this user?");
        alert.setPositiveButton("Yes", ((dialog, which) -> {
            db.delete("User", selection, selectionArgs);
            Toast.makeText(context, user.getFirstName() + " deleted!", Toast.LENGTH_SHORT).show();
        }));
        alert.setNegativeButton("No", ((dialog, which) -> Toast.makeText(context, "Aborting", Toast.LENGTH_SHORT).show()));
        alert.show();

        return user;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
