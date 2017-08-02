package com.yangqichao.bokuscience.business.ui.book;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.BookBean;
import com.yangqichao.bokuscience.business.bean.InitBookBean;
import com.yangqichao.bokuscience.business.bean.MyBookBean;
import com.yangqichao.bokuscience.common.APP;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class JournalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.recycle_book)
    RecyclerView recycleBook;
    @BindView(R.id.tv_seatch_type)
    TextView tvSeatchType;
    Unbinder unbinder;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;

    private BaseQuickAdapter<BookBean.RecordsBean, BaseViewHolder> adapter;
    private BaseQuickAdapter<MyBookBean.RecordsBean, BaseViewHolder> adapterMy;

    PopupWindow popupWindow;
    RecyclerView recyclerView;
    private BaseQuickAdapter<InitBookBean.SubjectsBean, BaseViewHolder> adapterType;
    InitBookBean.SubjectsBean bean;
    private List<InitBookBean.SubjectsBean> subjectListBean;

//    private BaseQuickAdapter<>

    // TODO: Rename and change types of parameters
    private boolean isMine;


    public JournalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initType();

        etSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    getdata();
                    hideSoftInputView();
                }
                return false;
            }
        });

        if (isMine) {
            adapterMy = new BaseQuickAdapter<MyBookBean.RecordsBean, BaseViewHolder>(R.layout.item_journal) {
                @Override
                protected void convert(BaseViewHolder helper, final MyBookBean.RecordsBean item) {
                    if(helper.getLayoutPosition()==adapterMy.getData().size()-1){
                        helper.setImageResource(R.id.img_book,R.drawable.btn_book_add);
                        helper.getView(R.id.img_book).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity(), BookMainActivity.class);
                                intent.putExtra("isJournal",true);
                                startActivity(intent);
                            }
                        });
                    }else{
                        helper.setText(R.id.tv_book_name, item.getBookDTO().getTitle());
                        helper.setText(R.id.tv_book_type, item.getBookDTO().getSubjectName());
                        Glide.with(getActivity()).load(item.getBookDTO().getImgUrl()).placeholder(R.drawable.icon_book_null)
                                .into((ImageView) helper.getView(R.id.img_book));
                        helper.getView(R.id.img_book).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                BookBean.RecordsBean recordsBean = new BookBean.RecordsBean();
                                recordsBean.setTitle(item.getBookDTO().getTitle());
                                recordsBean.setId(item.getBookId());
                                recordsBean.setFileUrl(item.getBookDTO().getFileUrl());
                                recordsBean.setIsAdd(1);
                                JournalActivity.starAction(getActivity(), recordsBean);
                            }
                        });
                    }

                }
            };

            recycleBook.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            recycleBook.setAdapter(adapterMy);
            adapterMy.setEmptyView(R.layout.empty_video_view, recycleBook);
        } else {
            adapter = new BaseQuickAdapter<BookBean.RecordsBean, BaseViewHolder>(R.layout.item_journal) {
                @Override
                protected void convert(BaseViewHolder helper, final BookBean.RecordsBean item) {
                    helper.setText(R.id.tv_book_name, item.getTitle());
                    helper.setText(R.id.tv_book_type, item.getSubjectName());
                    Glide.with(getActivity()).load(item.getImgUrl()).placeholder(R.drawable.icon_book_null)
                            .into((ImageView) helper.getView(R.id.img_book));
                    helper.getView(R.id.img_book).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            JournalActivity.starAction(getActivity(), item);
                        }
                    });
                }
            };

            recycleBook.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            recycleBook.setAdapter(adapter);
            adapter.setEmptyView(R.layout.empty_video_view, recycleBook);
        }


    }

    private void initType() {
        bean = new InitBookBean.SubjectsBean();
        bean.setSubName("全部");
        subjectListBean.add(0, bean);
        adapterType = new BaseQuickAdapter<InitBookBean.SubjectsBean, BaseViewHolder>(R.layout.item_param_search) {
            @Override
            protected void convert(BaseViewHolder helper, InitBookBean.SubjectsBean item) {
                helper.setText(R.id.tv_param, item.getSubName());
            }
        };

        adapterType.setNewData(subjectListBean);
        recyclerView = new RecyclerView(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapterType);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                bean = (InitBookBean.SubjectsBean) adapter.getData().get(position);
                tvSeatchType.setText(bean.getSubName());
                popupWindow.dismiss();
            }
        });

        popupWindow = new PopupWindow(recyclerView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                getdata();
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        getdata();
    }

    private void getdata() {
        String key = etSearch.getText().toString();
        RequestBody requestBody = new RequestBody();
        requestBody.setPage(1 + "");

        requestBody.setPageSize(100 + "");
        requestBody.setSearch(key);
        requestBody.setType("0");
        if (!bean.getSubName().equals("全部")) {
            requestBody.setSubjectId(bean.getId() + "");
        }
        if (isMine) {
            requestBody.setUserId(APP.getUserId());
            RequestUtil.createApi().selectMyBook(requestBody).compose(RequestUtil.<MyBookBean>handleResult())
                    .subscribe(new CommonsSubscriber<MyBookBean>() {
                        @Override
                        protected void onSuccess(MyBookBean bookBean) {
                            List<MyBookBean.RecordsBean> records = bookBean.getRecords();
                            records.add(new MyBookBean.RecordsBean());
                            adapterMy.setNewData(records);
                        }
                    });
        } else {
            requestBody.setUserid(APP.getUserId());
            RequestUtil.createApi().selectBook(requestBody).compose(RequestUtil.<BookBean>handleResult())
                    .subscribe(new CommonsSubscriber<BookBean>() {
                        @Override
                        protected void onSuccess(BookBean bookBean) {
                            adapter.setNewData(bookBean.getRecords());
                        }
                    });
        }

    }

    public static JournalFragment newInstance(boolean param1, List<InitBookBean.SubjectsBean> param2) {
        JournalFragment fragment = new JournalFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, (Serializable) param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isMine = getArguments().getBoolean(ARG_PARAM1);
            subjectListBean = (List<InitBookBean.SubjectsBean>) getArguments().getSerializable(ARG_PARAM2);
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

    @OnClick(R.id.tv_seatch_type)
    public void onViewClicked() {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAsDropDown(llSearch, -1000, 0);
        }
    }

    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getActivity().getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getActivity().getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
