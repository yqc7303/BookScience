package com.yangqichao.bokuscience.business.ui.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.MessageBean;
import com.yangqichao.bokuscience.common.APP;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MessageFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.recycle_message)
    RecyclerView recycleMessage;
    Unbinder unbinder;

    private boolean isHospital;

    private BaseQuickAdapter<MessageBean.RecordsBean,BaseViewHolder> adapter;
    private List<MessageBean.RecordsBean> records;


    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance(boolean isHospital) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, isHospital);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isHospital = getArguments().getBoolean(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new BaseQuickAdapter<MessageBean.RecordsBean, BaseViewHolder>(R.layout.item_message) {
            @Override
            protected void convert(BaseViewHolder helper, MessageBean.RecordsBean item) {
                helper.setText(R.id.tv_message_title,item.getTitle());
            }
        };
        recycleMessage.setAdapter(adapter);
        recycleMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleMessage.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                DynamicDetailActivity.startAction(getActivity(),records.get(position).getId()+"");
            }
        });
        getData();
    }

    private void getData() {
        RequestBody requestBody = new RequestBody();
        requestBody.setUserId(APP.getUserId());

        if(isHospital){
            requestBody.setType("1");
        }else{
            requestBody.setType("0");
        }
        RequestUtil.createApi().getbytype(requestBody).compose(RequestUtil.<MessageBean>handleResult())
                .subscribe(new CommonsSubscriber<MessageBean>() {
                    @Override
                    protected void onSuccess(MessageBean messageBean) {
                        records = messageBean.getRecords();
                        adapter.setNewData(records);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
