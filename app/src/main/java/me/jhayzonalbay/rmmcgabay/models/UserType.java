package me.jhayzonalbay.rmmcgabay.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class UserType implements Parcelable {

    public static final String ID = "id";
    public static final String TYPE = "type";

    private int id;

    private String type;

    public UserType() {

    }

    public UserType(String type) {
        this.type = type;
    }

    protected UserType(Parcel in) {
        id = in.readInt();
        type = in.readString();
    }

    public static final Creator<UserType> CREATOR = new Creator<UserType>() {
        @Override
        public UserType createFromParcel(Parcel in) {
            return new UserType(in);
        }

        @Override
        public UserType[] newArray(int size) {
            return new UserType[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(type);
    }
}
