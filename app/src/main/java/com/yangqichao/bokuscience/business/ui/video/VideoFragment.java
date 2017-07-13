package com.yangqichao.bokuscience.business.ui.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.InitVideoBean;
import com.yangqichao.bokuscience.business.bean.VideoListBean;
import com.yangqichao.bokuscience.common.APP;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VideoFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Unbinder unbinder;
    @BindView(R.id.recycleView_keshi)
    RecyclerView recycleViewKeshi;
    @BindView(R.id.recycleView_video)
    RecyclerView recycleViewVideo;


    private List<InitVideoBean.SubjectListBean> subjectListBeen;
    private int type;

    private SubjectAdapter adapterSubject;
    private BaseQuickAdapter<VideoListBean.RecordsBean, BaseViewHolder> adapterVideo;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapterSubject = new SubjectAdapter(R.layout.item_video_keshi,subjectListBeen);
        recycleViewKeshi.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleViewKeshi.setAdapter(adapterSubject);
        recycleViewKeshi.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                adapterSubject.setIndex(position);
                adapter.notifyDataSetChanged();
                //
                switchVideoList(subjectListBeen.get(position).getId());
            }
        });

        //

        adapterVideo = new BaseQuickAdapter<VideoListBean.RecordsBean, BaseViewHolder>(R.layout.item_video_list) {
            @Override
            protected void convert(BaseViewHolder helper, final VideoListBean.RecordsBean item) {
                helper.setText(R.id.tv_video_name,item.getTitle());
                helper.getView(R.id.img_video).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        VideoDetailActivity.startAction(getActivity(),item);
                    }
                });
            }
        };

        recycleViewVideo.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleViewVideo.setAdapter(adapterVideo);
        adapterVideo.setEmptyView(R.layout.empty_video_view,recycleViewVideo);
        switchVideoList(subjectListBeen.get(0).getId());
    }

    private void switchVideoList(int id) {
        RequestBody requestBody = new RequestBody();
        requestBody.setUserId(APP.getUserId());
        requestBody.setSubjectId(id+"");
        requestBody.setVideoType(type+"");

        requestBody.setPage(1+"");
        requestBody.setPageSize(100+"");
        RequestUtil.createApi().selectVideoList(requestBody).compose(RequestUtil.<VideoListBean>handleResult())
                .subscribe(new CommonsSubscriber<VideoListBean>() {
                    @Override
                    protected void onSuccess(VideoListBean videoListBean) {
                        adapterVideo.setNewData(videoListBean.getRecords());
                    }
                });
    }

    public static VideoFragment newInstance(List<InitVideoBean.SubjectListBean> param1, int param2) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            subjectListBeen = (List<InitVideoBean.SubjectListBean>) getArguments().getSerializable(ARG_PARAM1);
            type = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hospital_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class SubjectAdapter extends BaseQuickAdapter<InitVideoBean.SubjectListBean, BaseViewHolder>{

        private int index = 0;

        public void setIndex(int index) {
            this.index = index;
        }

        public SubjectAdapter(int layoutResId, List<InitVideoBean.SubjectListBean> data) {
            super(layoutResId, data);
        }


        @Override
        protected void convert(BaseViewHolder helper, InitVideoBean.SubjectListBean item) {
            if(helper.getLayoutPosition() == index){
                helper.setVisible(R.id.view_select,true);
                helper.setTextColor(R.id.tv_keshi_name,getColorResource(R.color.black));
                helper.setBackgroundColor(R.id.item_keshi,getColorResource(R.color.white));
            }else {
                helper.setVisible(R.id.view_select,false);
                helper.setTextColor(R.id.tv_keshi_name,getColorResource(R.color.base_text_gray_dark));
                helper.setBackgroundColor(R.id.item_keshi,getColorResource(R.color.base_bg_gray));
            }
            helper.setText(R.id.tv_keshi_name,item.getSubName());
        }
    }

    public int getColorResource(int color){
        return ContextCompat.getColor(getActivity(),color);
    }
}
