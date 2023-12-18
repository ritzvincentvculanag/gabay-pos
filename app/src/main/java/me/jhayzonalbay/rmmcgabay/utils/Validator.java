package me.jhayzonalbay.rmmcgabay.utils;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Validator {

    public static boolean fieldsAreValid(TextInputLayout ...  fields) {
        for (TextInputLayout field : fields) {
            if (Miner.getContent(field).isEmpty()) {
                field.setErrorEnabled(true);
                field.setError("Field is required");

                return false;
            }
        }

        return true;
    }

    public static void clearFields(TextInputLayout ... fields) {
        for (TextInputLayout field : fields) {
            Objects.requireNonNull(field.getEditText()).setText("");
        }
    }

}
