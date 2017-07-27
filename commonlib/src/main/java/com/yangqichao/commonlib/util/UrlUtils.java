package com.yangqichao.commonlib.util;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by yangqc on 2017/7/25.
 */

public class UrlUtils {

    //转换为%E4%BD%A0形式
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    public static String getpath(String url){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//获取网络视频
        retriever.setDataSource(url, new HashMap<String, String>());
//获取本地视频
//retriever.setDataSource(url);
        Bitmap bitmap = retriever.getFrameAtTime();
        FileOutputStream outStream = null;
        String basepath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "img";
        if(!new File(basepath).exists()){
            new File(basepath).mkdirs();
        }

        String imgName = url.substring(url.lastIndexOf("/") + 1);
        String imgPath = basepath + File.separator + imgName + ".jpg";
        try {

            outStream = new FileOutputStream(new File(imgPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, outStream);
        try {
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        retriever.release();
        return imgPath;
    };

}
