package me.jhayzonalbay.rmmcgabay.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {

    public static final String ID = "id";
    public static final String CATEGORY = "category_id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String BARCODE = "barcode";
    public static final String PRICE = "price";
    public static final String QUANTITY = "quantity";

    private Integer id;

    private String name;
    private String description;
    private String barcode;
    private Double price;
    private Integer quantity;
    private Category category;

    public Product() {

    }

    public Product(String name, String barcode, Double price, Category category) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        this.category = category;
        this.quantity = 1;
    }

    public Product(String name, String description, String barcode, Double price, Category category, Integer quantity) {
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    protected Product(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        description = in.readString();
        barcode = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
        if (in.readByte() == 0) {
            quantity = null;
        } else {
            quantity = in.readInt();
        }
        category = in.readParcelable(Category.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void increase() {
        this.quantity += 1;
    }

    public void decrease() {
        if (this.quantity == 1) {
            this.quantity = 1;
            return;
        }

        this.quantity -= 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(barcode);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(price);
        }
        if (quantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(quantity);
        }
        dest.writeParcelable(category, flags);
    }
}
