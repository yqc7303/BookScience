package com.yangqichao.bokuscience.business.ui.book;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.BookBean;
import com.yangqichao.bokuscience.business.bean.MyBookBean;
import com.yangqichao.bokuscience.common.APP;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class JournalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.et_)
    EditText et;
    @BindView(R.id.recycle_book)
    RecyclerView recycleBook;
    Unbinder unbinder;

    private BaseQuickAdapter<BookBean.RecordsBean, BaseViewHolder> adapter;
    private BaseQuickAdapter<MyBookBean.RecordsBean, BaseViewHolder> adapterMy;

    // TODO: Rename and change types of parameters
    private boolean isMine;
    private String mParam2;


    public JournalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(isMine){
            adapterMy = new BaseQuickAdapter<MyBookBean.RecordsBean, BaseViewHolder>(R.layout.item_book) {
                @Override
                protected void convert(BaseViewHolder helper, final MyBookBean.RecordsBean item) {
                    helper.setText(R.id.tv_book_name, item.getBookDTO().getTitle());
                    helper.setText(R.id.tv_book_type, item.getBookDTO().getSubjectName());
                    Glide.with(getActivity()).load(item.getBookDTO().getImgUrl())
                            .into((ImageView) helper.getView(R.id.img_book));
                    helper.getView(R.id.img_book).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            BookBean.RecordsBean recordsBean = new BookBean.RecordsBean();
                            recordsBean.setTitle(item.getBookDTO().getTitle());
                            recordsBean.setId(item.getBookDTO().getId());
                            recordsBean.setFileUrl(item.getBookDTO().getFileUrl());
                            recordsBean.setIsAdd(1);
                            JournalActivity.starAction(getActivity(),recordsBean);
                        }
                    });
                }
            };
            recycleBook.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            recycleBook.setAdapter(adapterMy);
        }else{
            adapter = new BaseQuickAdapter<BookBean.RecordsBean, BaseViewHolder>(R.layout.item_book) {
                @Override
                protected void convert(BaseViewHolder helper, final BookBean.RecordsBean item) {
                    helper.setText(R.id.tv_book_name, item.getTitle());
                    helper.setText(R.id.tv_book_type, item.getSubjectName());
                    Glide.with(getActivity()).load(item.getImgUrl())
                            .into((ImageView) helper.getView(R.id.img_book));
                    helper.getView(R.id.img_book).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            JournalActivity.starAction(getActivity(),item);
                        }
                    });
                }
            };
            recycleBook.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            recycleBook.setAdapter(adapter);
        }




    }

    @Override
    public void onResume() {
        super.onResume();
        getdata("");
    }

    private void getdata(String s) {
        RequestBody requestBody = new RequestBody();
        requestBody.setPage(1+"");
        requestBody.setUserId(APP.getUserId());
        requestBody.setPageSize(100+"");
        requestBody.setSearch(s);
        requestBody.setType("0");
        // TODO: 2017/7/12  subjectId
        if(isMine){
            RequestUtil.createApi().selectMyBook(requestBody).compose(RequestUtil.<MyBookBean>handleResult())
                    .subscribe(new CommonsSubscriber<MyBookBean>() {
                        @Override
                        protected void onSuccess(MyBookBean bookBean) {
                            adapterMy.setNewData(bookBean.getRecords());
                        }
                    });
        }else{

            RequestUtil.createApi().selectBook(requestBody).compose(RequestUtil.<BookBean>handleResult())
                    .subscribe(new CommonsSubscriber<BookBean>() {
                        @Override
                        protected void onSuccess(BookBean bookBean) {
                            adapter.setNewData(bookBean.getRecords());
                        }
                    });
        }

    }

    public static JournalFragment newInstance(boolean param1, String param2) {
        JournalFragment fragment = new JournalFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isMine = getArguments().getBoolean(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_journal, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
