package com.yangqichao.bokuscience.business.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.FeedBean;
import com.yangqichao.bokuscience.common.APP;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedBackActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycle_feed)
    RecyclerView recycleFeed;
    @BindView(R.id.et_feedback)
    EditText etFeedback;

    private MyAdapte adapter;
    private List<FeedBean> feedBeen;
    private List<FeedBean> feedBeenList;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        adapter = new MyAdapte(R.layout.item_feedback);

        recycleFeed.setLayoutManager(new LinearLayoutManager(this));
        recycleFeed.setAdapter(adapter);

        RequestUtil.createApi().getFeedList().compose(RequestUtil.<List<FeedBean>>handleResult())
                .subscribe(new CommonsSubscriber<List<FeedBean>>() {
                    @Override
                    protected void onSuccess(List<FeedBean> feedBeen) {
                        feedBeenList = feedBeen;
                        adapter.setNewData(feedBeen);
                    }
                });
    }


    @OnClick({R.id.img_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_submit:
//                "content": "string",
//                    "feedbackUserId": "string",
//                    "feedtype": "string"
                String content = etFeedback.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    showToast("请输入内容");
                    return;
                }
                if(adapter.getIndex() == -1){
                    showToast("请输入内容");
                    return;
                }
                RequestBody requestBody = new RequestBody();
                requestBody.setFeedbackUserId(APP.getUserId());
                requestBody.setFeedtype(feedBeenList.get(adapter.getIndex()).getFeedtype());
                requestBody.setContent(content);
                RequestUtil.createApi().addFeed(requestBody).compose(RequestUtil.<String>handleResult())
                        .subscribe(new CommonsSubscriber<String>() {
                            @Override
                            protected void onSuccess(String s) {
                                showToast("反馈已提交");
                                finish();
                            }
                        });

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class MyAdapte extends BaseQuickAdapter<FeedBean, BaseViewHolder> {

        private int index = -1;

        public MyAdapte(int layoutResId) {
            super(layoutResId);
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        protected void convert(final BaseViewHolder helper, FeedBean item) {
            helper.setText(R.id.tv_person_name, item.getFeedtypeName());
            if (helper.getLayoutPosition() == index) {
                helper.setImageResource(R.id.img_person_select, R.drawable.list_selected);
            } else {
                helper.setImageResource(R.id.img_person_select, R.drawable.list_select);
            }
            helper.getView(R.id.img_person_select).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index = helper.getLayoutPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }
}
