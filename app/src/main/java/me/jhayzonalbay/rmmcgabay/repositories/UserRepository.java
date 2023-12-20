package me.jhayzonalbay.rmmcgabay.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = Database.getReadableDatabase(context);
        Cursor cursor = db.query(
                "User",
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(User.ID));
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(User.FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow(User.LAST_NAME));
            String middleName = cursor.getString(cursor.getColumnIndexOrThrow(User.MIDDLE_NAME));
            String username = cursor.getString(cursor.getColumnIndexOrThrow(User.USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(User.PASSWORD));
            int userTypeId = cursor.getInt(cursor.getColumnIndexOrThrow(User.USER_TYPE));

            UserTypeRepository userTypeRepository = new UserTypeRepository(context);
            UserType userType = userTypeRepository.getUserType(userTypeId);

            User user = new User(firstName, lastName, middleName, username, password, userType);
            user.setId((int) id);
            users.add(user);
        }

        return users;
    }

    public User getUser(String username, String password) {
        User user = new User();
        SQLiteDatabase db = Database.getWritableDatabase(context);
        String selection = User.USERNAME + " LIKE ? AND " + User.PASSWORD + " LIKE ?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(
                "User",
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(User.ID));
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(User.FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow(User.LAST_NAME));
            String middleName = cursor.getString(cursor.getColumnIndexOrThrow(User.MIDDLE_NAME));
            String currentUsername = cursor.getString(cursor.getColumnIndexOrThrow(User.USERNAME));
            String currentPassword = cursor.getString(cursor.getColumnIndexOrThrow(User.PASSWORD));
            int userTypeId = cursor.getInt(cursor.getColumnIndexOrThrow(User.USER_TYPE));

            UserTypeRepository userTypeRepository = new UserTypeRepository(context);
            UserType userType = userTypeRepository.getUserType(userTypeId);

            user.setId((int) id);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setMiddleName(middleName);
            user.setUsername(currentUsername);
            user.setPassword(currentPassword);
            user.setUserType(userType);
        }

        return user;
    }

    public User getUser(String userId) {
        User user = new User();
        SQLiteDatabase db = Database.getWritableDatabase(context);
        String selection = User.USERNAME + " LIKE ? ";
        String[] selectionArgs = { userId };
        Cursor cursor = db.query(
                "User",
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(User.ID));
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(User.FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow(User.LAST_NAME));
            String middleName = cursor.getString(cursor.getColumnIndexOrThrow(User.MIDDLE_NAME));
            String currentUsername = cursor.getString(cursor.getColumnIndexOrThrow(User.USERNAME));
            String currentPassword = cursor.getString(cursor.getColumnIndexOrThrow(User.PASSWORD));
            int userTypeId = cursor.getInt(cursor.getColumnIndexOrThrow(User.USER_TYPE));

            UserTypeRepository userTypeRepository = new UserTypeRepository(context);
            UserType userType = userTypeRepository.getUserType(userTypeId);

            user.setId((int) id);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setMiddleName(middleName);
            user.setUsername(currentUsername);
            user.setPassword(currentPassword);
            user.setUserType(userType);
        }

        return user;
    }
}
