package com.yangqichao.commonlib.util;

import java.util.regex.Pattern;

/**
 * Created by yangqc on 2017/6/6.
 */

public class CheckUtils {
    public static boolean isPhoneValid(String name) {
        Pattern pattern= Pattern.compile("^(13[0-9]|14[5|7]|15\\d|17[6|7]|18[\\d])\\d{8}$");
        return pattern.matcher(name).matches();
    }

    public static boolean isLengthValid(String str,int length) {
        return str.length() >=length;
    }
}
