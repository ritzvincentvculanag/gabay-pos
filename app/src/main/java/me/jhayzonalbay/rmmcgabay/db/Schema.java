package me.jhayzonalbay.rmmcgabay.db;

import me.jhayzonalbay.rmmcgabay.models.Category;
import me.jhayzonalbay.rmmcgabay.models.Product;
import me.jhayzonalbay.rmmcgabay.models.PurchasedItem;
import me.jhayzonalbay.rmmcgabay.models.Invoice;
import me.jhayzonalbay.rmmcgabay.models.User;
import me.jhayzonalbay.rmmcgabay.models.UserType;

public class Schema {

    public static final String CREATE_CATEGORY = "CREATE TABLE Category (" +
            Category.ID + " INTEGER PRIMARY KEY NOT NULL," +
            Category.NAME + " TEXT NOT NULL" +
            ");";

    public static final String CREATE_USER_TYPE = "CREATE TABLE UserType(" +
            UserType.ID + " INTEGER PRIMARY KEY NOT NULL," +
            UserType.TYPE + " TEXT NOT NULL" +
            ");";

    public static final String CREATE_PRODUCT = "CREATE TABLE Product (" +
            Product.ID + " INTEGER PRIMARY KEY NOT NULL," +
            Product.CATEGORY + " INTEGER NOT NULL," +
            Product.NAME + " TEXT NOT NULL," +
            Product.DESCRIPTION + " TEXT NOT NULL," +
            Product.BARCODE + " TEXT NOT NULL," +
            Product.PRICE + " REAL NOT NULL," +
            "FOREIGN KEY (" + Product.CATEGORY + ") " +
            "REFERENCES Category (" + Category.ID + ")" +
            ");";

    public static final String CREATE_USER = "CREATE TABLE User (" +
            User.ID + " INTEGER PRIMARY KEY NOT NULL," +
            User.USER_TYPE + " INTEGER NOT NULL," +
            User.FIRST_NAME + " TEXT NOT NULL," +
            User.LAST_NAME+  " TEXT NOT NULL," +
            User.MIDDLE_NAME + " TEXT," +
            User.USERNAME + " TEXT NOT NULL," +
            User.PASSWORD + " TEXT NOT NULL," +
            "FOREIGN KEY (" + User.USER_TYPE + ") " +
            "REFERENCES UserType (" + UserType.ID + ")" +
            ");";

    public static final String CREATE_TRANSACTION = "CREATE TABLE Invoice (" +
            Invoice.ID + " INTEGER PRIMARY KEY NOT NULL," +
            Invoice.USER_ID + " INTEGER NOT NULL," +
            Invoice.TRANSACTION_DATE + " DATE NOT NULL," +
            Invoice.SUBTOTAL + " REAL NOT NULL," +
            "FOREIGN KEY (" + Invoice.USER_ID + ") " +
            "REFERENCES User (" + User.ID + ")" +
            ");";

    public static final String CREATE_PURCHASED_ITEM = "CREATE TABLE PurchasedItem (" +
            PurchasedItem.PRODUCT_ID + " INTEGER NOT NULL," +
            PurchasedItem.TRANSACTION_ID + " INTEGER NOT NULL," +
            "FOREIGN KEY (" + PurchasedItem.PRODUCT_ID + ")" +
            "REFERENCES Product (" + Product.ID + ")," +
            "FOREIGN KEY (" + PurchasedItem.TRANSACTION_ID + ")" +
            "REFERENCES Product (" + Invoice.ID + ")" +
            ");";

    public static final String DROP_CATEGORY = "DROP TABLE IF EXISTS Category";
    public static final String DROP_USER_TYPE = "DROP TABLE IF EXISTS UserType";
    public static final String DROP_PRODUCT = " DROP TABLE IF EXISTS Product";
    public static final String DROP_USER = "DROP TABLE IF EXISTS User";
    public static final String DROP_TRANSACTION = "DROP TABLE IF EXISTS Transaction";
    public static final String DROP_PURCHASED_ITEM = "DROP TABLE IF EXISTS PurchasedItem";
}
