package com.yangqichao.commonlib.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.yangqichao.commonlib.R;
import com.yangqichao.commonlib.widget.ZoomImageView;

import static com.yangqichao.commonlib.R.id.img_show;


public class ImageDisplayActivity extends AppCompatActivity {

    private ZoomImageView image;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display_main);


        url = getIntent().getStringExtra("url");
        image = (ZoomImageView)findViewById(img_show);
        Glide.with(this).load(url).into(image);
    }

    public static void startAction(Context context,String url){
        Intent intent = new Intent(context,ImageDisplayActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }


}
