package com.yangqichao.commonlib.util;

import android.app.Activity;
import android.view.WindowManager;

/**
 * Created by yangqc on 2017/3/30.
 */

public class WindowUtils {

    public static void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams layoutParams = context.getWindow().getAttributes();
        layoutParams.alpha = bgAlpha;// 0.0 - 1.0
        context.getWindow().setAttributes(layoutParams);
    }
}
