package com.yangqichao.bokuscience.business.ui.book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.BookBean;
import com.yangqichao.bokuscience.common.APP;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class JournalActivity extends BaseActivity {

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_shoucang)
    ImageView imgShoucang;

    private BookBean.RecordsBean bean;
    private boolean isAdd;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_common_web_view;
    }


    public static void starAction(Context context, BookBean.RecordsBean bean) {
        Intent intent = new Intent(context, JournalActivity.class);
        intent.putExtra("bean", bean);
        context.startActivity(intent);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        bean = (BookBean.RecordsBean) getIntent().getSerializableExtra("bean");

        if (bean == null) {
            finish();
        }
        String prdUrl = bean.getFileUrl();

        tvTitle.setText(bean.getTitle());
        isAdd = bean.getIsAdd() == 1;
        if (isAdd) {
            imgShoucang.setImageResource(R.drawable.btn_sc_pre);
        } else {
            imgShoucang.setImageResource(R.drawable.btn_sc);
        }
        webView.getSettings().setUseWideViewPort(true);  //将图片调整到适合webview的大小
        webView.getSettings().setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(prdUrl);

        webView.setWebChromeClient(new WebChromeClient());

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }


    @OnClick({R.id.img_back, R.id.img_shoucang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_shoucang:
                RequestBody requestBody = new RequestBody();
                requestBody.setUserId(APP.getUserId());
                requestBody.setBookId(bean.getId()+"");
                if(isAdd){
                    //取消收藏

                    RequestUtil.createApi().delete(requestBody).compose(RequestUtil.<String>handleResult())
                            .subscribe(new CommonsSubscriber<String>() {
                                @Override
                                protected void onSuccess(String s) {
                                    showToast("已取消收藏");
                                    imgShoucang.setImageResource(R.drawable.btn_sc);
                                    isAdd = false;
                                }
                            });
                }else{
                    //收藏
                    RequestUtil.createApi().add(requestBody).compose(RequestUtil.<String>handleResult())
                            .subscribe(new CommonsSubscriber<String>() {
                                @Override
                                protected void onSuccess(String s) {
                                    showToast("已收藏");
                                    imgShoucang.setImageResource(R.drawable.btn_sc_pre);
                                    isAdd = true;
                                }
                            });
                }
                break;
        }
    }
}
