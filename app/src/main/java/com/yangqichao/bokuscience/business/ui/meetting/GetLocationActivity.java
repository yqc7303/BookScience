package com.yangqichao.bokuscience.business.ui.meetting;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.commonlib.event.RxBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class GetLocationActivity extends BaseActivity implements Inputtips.InputtipsListener,TextWatcher{


    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.recycle_share)
    RecyclerView recycleShare;

    private BaseQuickAdapter<Tip,BaseViewHolder> adapter;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_get_location;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        etInput.addTextChangedListener(this);

        adapter = new BaseQuickAdapter<Tip, BaseViewHolder>(R.layout.item_location) {
            @Override
            protected void convert(BaseViewHolder helper, Tip item) {
                helper.setText(R.id.tv_location_name,item.getName());
                helper.setText(R.id.tv_location_address,item.getAddress());

            }
        };
        recycleShare.setLayoutManager(new LinearLayoutManager(this));
        recycleShare.setAdapter(adapter);
        recycleShare.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                RxBus.getDefault().post(adapter.getData().get(position));
                finish();
            }
        });
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        if(i == 1000){
            adapter.setNewData(list);
            Log.e("GetLocationActivity",list.toString());
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        InputtipsQuery inputquery = new InputtipsQuery(editable.toString(), "");
        Inputtips inputTips = new Inputtips(this, inputquery);
        inputTips.setInputtipsListener(this);
        inputTips.requestInputtipsAsyn();
    }
}
