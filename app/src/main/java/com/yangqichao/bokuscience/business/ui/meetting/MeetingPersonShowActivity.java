package com.yangqichao.bokuscience.business.ui.meetting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.ShowPersonBean;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestUtil;
import com.yangqichao.bokuscience.common.utils.IntentUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MeetingPersonShowActivity extends BaseActivity {

    @BindView(R.id.tv_keshi_name)
    TextView tvKeshiName;
    @BindView(R.id.recycle_keshi)
    RecyclerView recycleKeshi;

    private BaseQuickAdapter<ShowPersonBean.RecordsBean,BaseViewHolder> adapter;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_meeting_person_show;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        adapter = new BaseQuickAdapter<ShowPersonBean.RecordsBean, BaseViewHolder>(R.layout.item_show_person) {
            @Override
            protected void convert(BaseViewHolder helper, final ShowPersonBean.RecordsBean item) {
                helper.setText(R.id.tv_person_name,item.getUserName());
                helper.setText(R.id.tv_person_tel,item.getUserTel());
                boolean isSign = item.getSignflag() == 1;
                if(!isSign){
                    helper.setText(R.id.tv_sign_status,"未签到");
                    helper.setTextColor(R.id.tv_sign_status, getColorResource(R.color.red));
                    helper.setVisible(R.id.tv_sign_time,false);
                    helper.setTextColor(R.id.tv_person_name,getColorResource(R.color.base_text_black));
                    helper.setTextColor(R.id.tv_person_tel,getColorResource(R.color.blue));
                    helper.getView(R.id.tv_person_tel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            IntentUtil.callPhone(MeetingPersonShowActivity.this,item.getUserTel());
                        }
                    });
                }else{
                    helper.setText(R.id.tv_sign_status,"已签到");
                    helper.setTextColor(R.id.tv_sign_status,getColorResource(R.color.base_text_gray_dark));
                    helper.setVisible(R.id.tv_sign_time,true);
                    helper.setTextColor(R.id.tv_person_name,getColorResource(R.color.base_text_gray_dark));
                    helper.setTextColor(R.id.tv_person_tel,getColorResource(R.color.base_text_gray_dark));
                    helper.setText(R.id.tv_sign_time,item.getShowSign());
                }
            }
        };

        recycleKeshi.setLayoutManager(new LinearLayoutManager(this));
        recycleKeshi.setAdapter(adapter);

        RequestUtil.createApi().showPerson(getIntent().getStringExtra("meetingId")).compose(RequestUtil.<ShowPersonBean>handleResult())
                .subscribe(new CommonsSubscriber<ShowPersonBean>() {
                    @Override
                    protected void onSuccess(ShowPersonBean showPersonBean) {
                        adapter.setNewData(showPersonBean.getRecords());
                    }
                });
    }

    public static void startAction(Context context,String meetingId){
        Intent intent = new Intent(context,MeetingPersonShowActivity.class);
        intent.putExtra("meetingId",meetingId);
        context.startActivity(intent);
    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
