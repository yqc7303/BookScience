package com.yangqichao.commonlib.widget;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by yangqc on 2017/6/6.
 */

public class CleanTextWatcher implements TextWatcher {

    private TextInputLayout textInputLayout;

    public CleanTextWatcher(TextInputLayout inputLayout) {
        textInputLayout = inputLayout;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(textInputLayout.getError()!=null){
            textInputLayout.setError(null);
        }
    }

}
