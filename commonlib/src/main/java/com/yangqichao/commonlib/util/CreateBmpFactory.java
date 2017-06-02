package com.yangqichao.commonlib.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.util.UUID;

/**
 * Created by TAO on 2016/8/3.
 */
public class CreateBmpFactory {
    // ----------相机图片的业务相关
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择

    private Fragment fragment;
    private Activity activity;
    private File tempFile;

    public CreateBmpFactory(Fragment fragment) {
        super();
        this.fragment = fragment;
        WindowManager wm = (WindowManager) fragment.getActivity()
                .getSystemService(Context.WINDOW_SERVICE);
        windowHeight = wm.getDefaultDisplay().getHeight();
        windowWidth = wm.getDefaultDisplay().getWidth();
    }

    int windowHeight;
    int windowWidth;

    public CreateBmpFactory(Activity activity) {
        super();
        this.activity = activity;
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        windowHeight = wm.getDefaultDisplay().getHeight();
        windowWidth = wm.getDefaultDisplay().getWidth();
    }

    public void OpenGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        if (fragment != null) {
            fragment.startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
        } else {
            activity.startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
        }
    }

    public void OpenCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (hasSdcard()) {
            tempFile = new File(Environment.getExternalStorageDirectory(), UUID
                    .randomUUID().toString() + ".png");
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        if (fragment != null) {
            fragment.startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
        } else {
            activity.startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
        }
    }

    public String getBitmapFilePath(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                Uri uri = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = null;
                if (fragment != null) {
                    cursor = fragment.getActivity().getContentResolver()
                            .query(uri, filePathColumn, null, null, null);
                } else {
                    cursor = activity.getContentResolver().query(uri,
                            filePathColumn, null, null, null);
                }
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                return picturePath;
            }
        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            if (hasSdcard()) {
                return tempFile.getAbsolutePath();
            } else {
                if (fragment != null) {
                    Toast.makeText(fragment.getActivity(), "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(activity, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return null;
    }

    private int aspectX = 0;
    private int aspectY = 0;

    public Bitmap getBitmapByOpt(String picturePath) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(picturePath, opt);
        int imgHeight = opt.outHeight;
        int imgWidth = opt.outWidth;
        int scaleX = (int) (imgWidth /(float)(windowWidth/8));
        int scaleY = (int) (imgHeight / (float)(windowHeight/8));
        int scale = 1;
        if (scaleX > scaleY & scaleY >= 1) {
            scale = scaleX;
        }
        if (scaleY > scaleX & scaleX >= 1) {
            scale = scaleY;
        }
        opt.inJustDecodeBounds = false;
        opt.inSampleSize = scale;
        return BitmapFactory.decodeFile(picturePath, opt);
    }

    private boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }
}
