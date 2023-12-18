package me.jhayzonalbay.rmmcgabay.repositories;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import me.jhayzonalbay.rmmcgabay.db.Database;
import me.jhayzonalbay.rmmcgabay.models.UserType;

public class UserTypeRepository implements CrudRepository<UserType> {

    private Context context;

    public UserTypeRepository(Context context) {
        this.context = context;
    }

    @Override
    public UserType insert(UserType userType) {
        return null;
    }

    @Override
    public UserType update(UserType userType) {
        return null;
    }

    @Override
    public UserType delete(UserType userType) {
        return null;
    }

    @Override
    public List<UserType> getAll() {
        List<UserType> userTypes = new ArrayList<>();
        SQLiteDatabase db = Database.getReadableDatabase(context);
        Cursor cursor = db.query(
                "UserType",
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(UserType.ID));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(UserType.TYPE));

            UserType userType = new UserType(type);
            userType.setId((int) id);
            userTypes.add(userType);
        }

        return userTypes;
    }

    public UserType getUserType(String type) {
        UserType userType = new UserType();
        SQLiteDatabase db = Database.getWritableDatabase(context);
        String selection = UserType.TYPE + " LIKE ?";
        String[] selectionArgs = { type };
        Cursor cursor = db.query(
                "UserType",
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            long currentId = cursor.getLong(cursor.getColumnIndexOrThrow(UserType.ID));
            String userTypeType = cursor.getString(cursor.getColumnIndexOrThrow(UserType.TYPE));

            userType.setId((int) currentId);
            userType.setType(userTypeType);
        }

        return userType;
    }
}
