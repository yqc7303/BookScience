package com.yangqichao.bokuscience.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by yangqc on 2017/7/9.
 */

public class IntentUtil {

    public static void callPhone(Context context,String phone){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
        context.startActivity(intent);
    }

}

