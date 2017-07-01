package com.yangqichao.bokuscience.common.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.yangqichao.bokuscience.R;

/**
 * Created by yangqc on 2017/7/1.
 */

public class DateTimeChoose extends AlertDialog{

    private DatePicker datePicker;
    private TimePicker timePicker;

    public DateTimeChoose(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_time_dialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(false);
    }
}
