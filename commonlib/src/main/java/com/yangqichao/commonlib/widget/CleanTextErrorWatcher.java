package com.yangqichao.commonlib.widget;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yangqc on 2017/6/6.
 */

public class CleanTextErrorWatcher implements TextWatcher {

    private TextInputLayout textInputLayout;
    private TextView textView;

    public CleanTextErrorWatcher(TextInputLayout textInputLayout, TextView textView) {
        this.textInputLayout = textInputLayout;
        this.textView = textView;
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
        if(textView.getVisibility() == View.VISIBLE){
            textView.setVisibility(View.GONE);
        }
    }

}
