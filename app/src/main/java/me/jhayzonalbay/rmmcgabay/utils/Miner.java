package me.jhayzonalbay.rmmcgabay.utils;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Miner {

    public static String getContent(TextInputLayout field) {
        return Objects.requireNonNull(field.getEditText()).getText().toString();
    }

}
