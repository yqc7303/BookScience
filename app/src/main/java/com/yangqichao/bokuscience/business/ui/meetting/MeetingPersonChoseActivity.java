package com.yangqichao.bokuscience.business.ui.meetting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.GetKeShiPerson;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MeetingPersonChoseActivity extends BaseActivity {


    @BindView(R.id.tv_keshi_name)
    TextView tvKeshiName;
    @BindView(R.id.recycle_keshi)
    RecyclerView recycleKeshi;

    private String deptId,index;

    BaseQuickAdapter<GetKeShiPerson.RecordsBean,BaseViewHolder> adapter;
    private List<GetKeShiPerson.RecordsBean> records;

    private List<GetKeShiPerson.RecordsBean> chooseList;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_meeting_person_chose;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        deptId = getIntent().getStringExtra("detIp");
        index = getIntent().getStringExtra("index");
        tvKeshiName.setText(getIntent().getStringExtra("name"));

        adapter = new BaseQuickAdapter<GetKeShiPerson.RecordsBean, BaseViewHolder>(R.layout.item_keshi_person) {
            @Override
            protected void convert(final BaseViewHolder helper, final GetKeShiPerson.RecordsBean item) {
                helper.setText(R.id.tv_person_name,item.getName());
                helper.setText(R.id.tv_person_tel,item.getTel());
                if(item.isSelect()){
                    helper.setImageResource(R.id.img_person_select,R.drawable.icon_select_pre);
                }else{
                    helper.setImageResource(R.id.img_person_select,R.drawable.icon_select_nor);
                }
                helper.getView(R.id.img_person_select).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(item.isSelect()){
                            item.setSelect(false);
                            helper.setImageResource(R.id.img_person_select,R.drawable.icon_select_nor);
                        }else{
                            item.setSelect(true);
                            helper.setImageResource(R.id.img_person_select,R.drawable.icon_select_pre);
                        }

                    }
                });
            }
        };
        recycleKeshi.setLayoutManager(new LinearLayoutManager(this));
        recycleKeshi.setAdapter(adapter);

        RequestBody requestBody = new RequestBody();
        requestBody.setDeptId(deptId);
        requestBody.setPage(1+"");
        requestBody.setPageSize(100+"");
        RequestUtil.createApi().users(requestBody).compose(RequestUtil.<GetKeShiPerson>handleResult())
                .subscribe(new CommonsSubscriber<GetKeShiPerson>() {
                    @Override
                    protected void onSuccess(GetKeShiPerson getKeShiPerson) {
                        records = getKeShiPerson.getRecords();
                        adapter.setNewData(records);
                    }
                });
    }


    @OnClick({R.id.img_back, R.id.tv_submit_person})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_submit_person:
                chooseList = new ArrayList<>();
                for(GetKeShiPerson.RecordsBean recordsBean:records){
                    if(recordsBean.isSelect()){
                        chooseList.add(recordsBean);
                    }
                }
                Intent intent = new Intent(this,MeetingActivity.class);
                intent.putExtra("index",index);
                intent.putExtra("choose", (Serializable) chooseList);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
