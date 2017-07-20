package com.yangqichao.bokuscience.business.ui.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.MessageDetail;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class DynamicDetailActivity extends BaseActivity {


    @BindView(R.id.tv_message_title)
    TextView tvMessageTitle;
    @BindView(R.id.tv_message_time)
    TextView tvMessageTime;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.tv_message_content)
    TextView tvMessageContent;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_dynamic_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        String id = getIntent().getStringExtra("id");

        RequestUtil.createApi().getMessageDetail(id).compose(RequestUtil.<MessageDetail>handleResult())
                .subscribe(new CommonsSubscriber<MessageDetail>() {
                    @Override
                    protected void onSuccess(MessageDetail messageDetail) {
                        tvMessageTitle.setText(messageDetail.getTitle());
                        tvMessageTime.setText(messageDetail.getGmtCreate());
                        Glide.with(DynamicDetailActivity.this).load(messageDetail.getImgUrl())
                                .into(imgMessage);
                        tvMessageContent.setText(messageDetail.getContent());
                    }
                });
    }

    public static void startAction(Context context,String id){
        Intent intent = new Intent(context,DynamicDetailActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
