package me.jhayzonalbay.rmmcgabay.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import me.jhayzonalbay.rmmcgabay.models.User;
import me.jhayzonalbay.rmmcgabay.models.UserType;
import me.jhayzonalbay.rmmcgabay.repositories.UserRepository;
import me.jhayzonalbay.rmmcgabay.repositories.UserTypeRepository;

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "Gabay";
    private static final int DB_VERSION = 1;
    private static final SQLiteDatabase.CursorFactory DB_FACTORY = null;
    private static final Object MONITOR = new Object();
    private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;
    private Context context;

    public static volatile Database instance = null;

    public Database(@Nullable Context context) {
        super(context, DB_NAME, DB_FACTORY, DB_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Schema.CREATE_CATEGORY);
        db.execSQL(Schema.CREATE_USER_TYPE);
        db.execSQL(Schema.CREATE_PRODUCT);
        db.execSQL(Schema.CREATE_USER);
        db.execSQL(Schema.CREATE_TRANSACTION);
        db.execSQL(Schema.CREATE_PURCHASED_ITEM);

        setupUserTypes(db);
        setupUsers(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Schema.DROP_CATEGORY);
        db.execSQL(Schema.DROP_USER_TYPE);
        db.execSQL(Schema.DROP_PRODUCT);
        db.execSQL(Schema.DROP_USER);
        db.execSQL(Schema.DROP_TRANSACTION);
        db.execSQL(Schema.DROP_PURCHASED_ITEM);

        onCreate(db);
    }

    public static SQLiteDatabase getWritableDatabase(Context context) {
        if (instance == null) {
            synchronized (MONITOR) {
                if (instance == null) {
                    instance = new Database(context);
                }
            }
        }

        return instance.getWritableDatabase();
    }

    public static SQLiteDatabase getReadableDatabase(Context context) {
        if (instance == null) {
            synchronized (MONITOR) {
                if (instance == null) {
                    instance = new Database(context);
                }
            }
        }

        return instance.getReadableDatabase();
    }


    public void setupUserTypes(SQLiteDatabase db) {

        userTypeRepository = new UserTypeRepository(context);
        db.execSQL("INSERT INTO UserType (type) VALUES ('Admin')");
        db.execSQL("INSERT INTO UserType (type) VALUES ('Cashier')");
    }

    public void setupUsers(SQLiteDatabase db) {
        userRepository = new UserRepository(context);
        db.execSQL("INSERT INTO User (first_name, last_name, username, password, user_type) " +
                "VALUES ('Admin', 'Admin', 'admin', 'admin', 1)");

        db.execSQL("INSERT INTO User (first_name, last_name, username, password, user_type) " +
                "VALUES ('Cashier', 'Cashier', 'cashier', 'cashier', 2)");
    }

}
