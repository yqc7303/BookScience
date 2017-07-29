package com.yangqichao.bokuscience.business.ui.meetting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.GetKeShiPerson;
import com.yangqichao.bokuscience.business.bean.LevelBean;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestUtil;
import com.yangqichao.commonlib.util.PreferenceUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 参会人员
 */
public class MeetingPersonActivity extends BaseActivity {


    @BindView(R.id.recycle_keshi)
    RecyclerView recycleKeshi;

    private BaseQuickAdapter<LevelBean, BaseViewHolder> adapter;
    List<LevelBean> list;

    private List<GetKeShiPerson.RecordsBean> chooseList;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_meeting_participant;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        adapter = new BaseQuickAdapter<LevelBean, BaseViewHolder>(R.layout.item_keshi) {
            @Override
            protected void convert(BaseViewHolder helper, LevelBean item) {
                helper.setText(R.id.tv_keshi_name, item.getOrgName());
                if (item.getKeShiPerson() == null) {

                    helper.setText(R.id.tv_keshi_choose, "请选择");
                } else {
                    helper.setText(R.id.tv_keshi_choose, "已选" + item.getKeShiPerson().size() + "人");
                }
            }
        };

        recycleKeshi.setLayoutManager(new LinearLayoutManager(this));
        recycleKeshi.setAdapter(adapter);

        RequestUtil.createApi().getLevel(PreferenceUtils.getPrefString(this, "hospitalId", "")).compose(RequestUtil.<List<LevelBean>>handleResult())
                .subscribe(new CommonsSubscriber<List<LevelBean>>() {
                    @Override
                    protected void onSuccess(List<LevelBean> levelBeen) {
                        list = levelBeen;
                        adapter.setNewData(levelBeen);
                    }
                });

        recycleKeshi.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MeetingPersonActivity.this, MeetingPersonChoseActivity.class);
                intent.putExtra("index", position);
                intent.putExtra("detIp", list.get(position).getId());
                intent.putExtra("name", list.get(position).getOrgName());
                startActivityForResult(intent, 100);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            int pos = data.getIntExtra("pos", 0);
            List<GetKeShiPerson.RecordsBean> recordsBean = (List<GetKeShiPerson.RecordsBean>) data.getSerializableExtra("choose");
            list.get(pos).setKeShiPerson(recordsBean);
            adapter.notifyDataSetChanged();
        }
    }


    @OnClick({R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                chooseList = new ArrayList<>();
                for(LevelBean levelBean:list){
                    if(levelBean.getKeShiPerson()!=null){
                        chooseList.addAll(levelBean.getKeShiPerson());
                    }
                }

                Intent intent = new Intent(this,MeetingActivity.class);
                intent.putExtra("choose", (Serializable) chooseList);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
