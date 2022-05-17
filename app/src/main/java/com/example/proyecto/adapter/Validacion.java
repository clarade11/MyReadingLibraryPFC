package com.example.proyecto.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class Validacion {
    private Context context;
    /**
     * constructor
     *
     * @param context
     */
    public Validacion(Context context) {
        this.context = context;
    }
    /**
     * method to check InputEditText filled .
     *
     * @param editText
     *
     * @param message
     * @return
     */
    public boolean isEditText(EditText editText, String message) {
        String value = editText.getText().toString().trim();
        if (value.isEmpty()) {
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.show();
            hideKeyboardFrom(editText);
            return false;
        } else {
//            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * method to check InputEditText has valid email .
     *
     * @param editText
     * @param message
     * @return
     */
    public boolean isEditTextUsuario(EditText editText,  String message) {
        String value = editText.getText().toString().trim();
        if (value.isEmpty() ) {
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.show();
            hideKeyboardFrom(editText);
            return false;
        } else {
            //textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
    public boolean isEditTextContrasena(EditText editText1, EditText editText2,  String message) {
        String value1 = editText1.getText().toString().trim();
        String value2 = editText2.getText().toString().trim();
        if (!value1.equals(value2)) {
            hideKeyboardFrom(editText2);
            return false;
        } else {
            //textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
    /**
     * method to Hide keyboard
     *
     * @param view
     */
    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
