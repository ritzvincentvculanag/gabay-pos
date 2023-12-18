package me.jhayzonalbay.rmmcgabay.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.util.List;

public class Transaction implements Parcelable {

    private int id;
    private int userId;

    private List<Product> products;
    private LocalDate transactionDate;
    private Double subTotal;

    public Transaction() {

    }

    public Transaction(List<Product> products, LocalDate transactionDate, Double subTotal) {
        this.products = products;
        this.transactionDate = transactionDate;
        this.subTotal = subTotal;
    }

    protected Transaction(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        products = in.createTypedArrayList(Product.CREATOR);
        if (in.readByte() == 0) {
            subTotal = null;
        } else {
            subTotal = in.readDouble();
        }
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
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
}
