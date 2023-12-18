package me.jhayzonalbay.rmmcgabay.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice implements Parcelable {

    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String TRANSACTION_DATE = "transaction_date";
    public static final String SUBTOTAL = "subtotal";

    private int id;
    private int userId;

    private List<Product> products;
    private LocalDate transactionDate;
    private Double subTotal;

    public Invoice() {
        this.products = new ArrayList<>();
        this.subTotal = 0d;
    }

    public Invoice(List<Product> products, LocalDate transactionDate, Double subTotal) {
        this.products = products;
        this.transactionDate = transactionDate;
        this.subTotal = subTotal;
    }

    protected Invoice(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        products = in.createTypedArrayList(Product.CREATOR);
        if (in.readByte() == 0) {
            subTotal = null;
        } else {
            subTotal = in.readDouble();
        }
    }

    public static final Creator<Invoice> CREATOR = new Creator<Invoice>() {
        @Override
        public Invoice createFromParcel(Parcel in) {
            return new Invoice(in);
        }

        @Override
        public Invoice[] newArray(int size) {
            return new Invoice[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(userId);
        dest.writeTypedList(products);
        if (subTotal == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(subTotal);
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", userId=" + userId +
                ", products=" + products +
                ", transactionDate=" + transactionDate +
                ", subTotal=" + subTotal +
                '}';
    }

    public void clear() {
        this.products.clear();
    }
}
