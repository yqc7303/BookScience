package com.yangqichao.bokuscience.business.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class WWQKWebViewActivity extends BaseActivity {

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_shoucang)
    ImageView imgShoucang;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_common_web_view;
    }


    public static void starAction(Context context, String url, String title,
                                  String phone,String code) {
        Intent intent = new Intent(context, WWQKWebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("phone", phone);
        intent.putExtra("code", code);
        context.startActivity(intent);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        String phone = getIntent().getStringExtra("phone");
        String code = getIntent().getStringExtra("code");
        String prdUrl = getIntent().getStringExtra("url");
        if (TextUtils.isEmpty(prdUrl)) {
            finish();
        }
        tvTitle.setText(getIntent().getStringExtra("title"));
        webView.getSettings().setUseWideViewPort(true);  //将图片调整到适合webview的大小
        webView.getSettings().setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webView.getSettings().setJavaScriptEnabled(true);

        String urlStr = prdUrl+"?"+"useremail="+ Base64.encodeToString(phone.getBytes(), Base64.DEFAULT)
                +"&libid="+Base64.encodeToString(code.getBytes(), Base64.DEFAULT);
        webView.loadUrl(urlStr);

        webView.setWebChromeClient(new WebChromeClient());

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });
    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
