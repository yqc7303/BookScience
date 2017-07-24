package com.yangqichao.bokuscience.business.ui.meetting;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.commonlib.event.RxBus;

import butterknife.BindView;
import butterknife.OnClick;


public class GetLocationActivity extends BaseActivity implements PoiSearch.OnPoiSearchListener,TextWatcher{


    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.recycle_share)
    RecyclerView recycleShare;

    private BaseQuickAdapter<PoiItem,BaseViewHolder> adapter;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_get_location;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        etInput.addTextChangedListener(this);

        adapter = new BaseQuickAdapter<PoiItem, BaseViewHolder>(R.layout.item_location) {
            @Override
            protected void convert(BaseViewHolder helper, PoiItem item) {
                helper.setText(R.id.tv_location_name,item.getTitle());
                helper.setText(R.id.tv_location_address,item.getSnippet());

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
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        PoiSearch.Query query = new PoiSearch.Query(editable.toString(),"");
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(1);
        PoiSearch poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        adapter.setNewData(poiResult.getPois());
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
