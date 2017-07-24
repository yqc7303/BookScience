package com.yangqichao.bokuscience.business.ui.book;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.Toast;

import com.arialyy.aria.core.Aria;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.folioreader.activity.FolioActivity;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.BookBean;
import com.yangqichao.bokuscience.business.bean.InitBookBean;
import com.yangqichao.bokuscience.business.bean.MyBookBean;
import com.yangqichao.bokuscience.common.APP;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;
import com.yangqichao.commonlib.util.PreferenceUtils;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class BooKFragment extends Fragment {
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
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    Unbinder unbinder;

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
    private String basePath;

    public BooKFragment() {
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



//        basePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "tmp2";
        basePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "book"
                +File.separator;
        Aria.download(this).register();
        if (isMine) {
            adapterMy = new BaseQuickAdapter<MyBookBean.RecordsBean, BaseViewHolder>(R.layout.item_book) {
                @Override
                protected void convert(final BaseViewHolder helper, final MyBookBean.RecordsBean item) {


                    helper.setText(R.id.tv_book_name, item.getBookDTO().getTitle());
                    helper.setText(R.id.tv_book_type, item.getBookDTO().getSubjectName());
                    Glide.with(getActivity()).load(item.getBookDTO().getImgUrl()).placeholder(R.drawable.icon_book_null)
                            .into((ImageView) helper.getView(R.id.img_book));

                    final String path = basePath + item.getId();
                    final String fileUrl = item.getBookDTO().getFileUrl();
                    final String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
                    if(!new File(path).exists()){
                        new File(path).mkdirs();
                    }

                    boolean isdone = PreferenceUtils.getPrefBoolean(getActivity(), "book" + item.getId(), false);
                    item.getBookDTO().setDone(isdone);
                    if (item.getBookDTO().isDone()) {
                        //已存在
                        helper.getView(R.id.img_book).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity(), FolioActivity.class);
                                intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_TYPE,FolioActivity.EpubSourceType.SD_CARD);
                                intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_PATH, path+File.separator+fileName);
                                intent.putExtra("title",fileName);
                                intent.putExtra("isAdd",true);
                                startActivityForResult(intent,item.getBookId());
                            }
                        });
                        helper.setVisible(R.id.tv_download, false);
                    } else {
                        helper.getView(R.id.img_book).setOnClickListener(null);
                        helper.setVisible(R.id.tv_download, true);
                        helper.getView(R.id.tv_download).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String path = basePath + item.getBookDTO().getId();
                                if(!new File(path).exists()){
                                    new File(path).mkdirs();
                                }
                                download(path+File.separator+fileName, item.getBookDTO().getFileUrl(),item.getId(),(TextView) helper.getView(R.id.tv_download),fileName,1);

//                                basePath + item.getId()+File.separator+ fileName,
//                                        fileUrl,item.getId()
                            }
                        });
                    }

                }

            };
            recycleBook.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            recycleBook.setAdapter(adapterMy);
            adapterMy.setEmptyView(R.layout.empty_video_view, recycleBook);
        } else {
            adapter = new BaseQuickAdapter<BookBean.RecordsBean, BaseViewHolder>(R.layout.item_book) {
                @Override
                protected void convert(final BaseViewHolder helper, final BookBean.RecordsBean item) {
                    helper.setText(R.id.tv_book_name, item.getTitle());
                    helper.setText(R.id.tv_book_type, item.getSubjectName());
                    Glide.with(getActivity()).load(item.getImgUrl()).placeholder(R.drawable.icon_book_null)
                            .into((ImageView) helper.getView(R.id.img_book));
                    final String path = basePath + item.getId();
                    final String fileUrl = item.getFileUrl();
                    final String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
                    if(!new File(path).exists()){
                        new File(path).mkdirs();
                    }

                    boolean prefBoolean = PreferenceUtils.getPrefBoolean(getActivity(), "book" + item.getId(), false);
                    item.setDone(prefBoolean);
                    if (item.isDone()) {
                        //已存在
                        helper.getView(R.id.img_book).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity(), FolioActivity.class);
                                intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_TYPE,FolioActivity.EpubSourceType.SD_CARD);
                                intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_PATH, path+File.separator+fileName);
                                intent.putExtra("title",fileName);
                                intent.putExtra("isAdd",item.getIsAdd()==1);
                                startActivityForResult(intent,item.getId());
                            }
                        });
                        helper.setVisible(R.id.tv_download, false);
                    } else {
                        //去下载
                        helper.setText(R.id.tv_download,"下载");
                        helper.getView(R.id.img_book).setOnClickListener(null);
                        helper.setVisible(R.id.tv_download, true);
                        helper.getView(R.id.tv_download).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                download(basePath + item.getId()+File.separator+ fileName,
                                        fileUrl,item.getId(),((TextView) helper.getView(R.id.tv_download)),fileName,item.getIsAdd());
                            }
                        });
                    }
                }
            };
            recycleBook.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            recycleBook.setAdapter(adapter);
            adapter.setEmptyView(R.layout.empty_video_view, recycleBook);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == getActivity().RESULT_OK){
            RequestBody requestBody = new RequestBody();
            requestBody.setBookId(requestCode+"");
            requestBody.setUserId(APP.getUserId());
            if(data.getBooleanExtra("isAdd",false)){
                RequestUtil.createApi().add(requestBody).compose(RequestUtil.<String>handleResult())
                        .subscribe(new CommonsSubscriber<String>() {
                            @Override
                            protected void onSuccess(String s) {

                            }
                        });
            }else {
                RequestUtil.createApi().delete(requestBody).compose(RequestUtil.<String>handleResult())
                        .subscribe(new CommonsSubscriber<String>() {
                            @Override
                            protected void onSuccess(String s) {

                            }
                        });
            }

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
    }

    boolean downAble = true;
    private void download(final String path, String url, final int id, final TextView textview, final String fileName, final int isAdd) {
//        Aria.download(this)
//                .load(url)     //读取下载地址
//                .setDownloadPath(path)    //设置文件保存的完整路径
//                .start();
        if(!downAble){
            Toast.makeText(getActivity(),"请稍后",Toast.LENGTH_SHORT).show();
            return;
        }
        downAble = false;
        FileDownloader.setup(getActivity());
        String encodeUrl = null;
        url = url.substring(0,url.lastIndexOf("/")+1);
        try {

            encodeUrl = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        FileDownloader.getImpl().create(url+encodeUrl)
                .setPath(path)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        System.out.println();
                    }

                    @Override
                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
                        System.out.println();
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, final int soFarBytes, final int totalBytes) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textview.setText(soFarBytes/totalBytes+" %");
                            }
                        });


                    }

                    @Override
                    protected void blockComplete(BaseDownloadTask task) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textview.setText("100%");
                            }
                        });

                    }

                    @Override
                    protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
                        System.out.println();
                        downAble = true;
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        downAble = true;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                PreferenceUtils.setPrefBoolean(getActivity(),"book"+id,true);

                                textview.setVisibility(View.GONE);
//                                if(isMine){
//                                    adapterMy.notifyDataSetChanged();
//                                }else{
//                                    adapter.notifyDataSetChanged();
//                                }

                                Intent intent = new Intent(getActivity(), FolioActivity.class);
                                intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_TYPE,FolioActivity.EpubSourceType.SD_CARD);
                                intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_PATH, path);
                                intent.putExtra("title",fileName);
                                intent.putExtra("isAdd",isAdd);
                                startActivityForResult(intent,id);

                            }
                        });
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        System.out.println();
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        downAble = true;
                        Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        downAble = true;
                        System.out.println();
                    }
                }).start();

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
        requestBody.setType("1");
        if (!bean.getSubName().equals("全部")) {
            requestBody.setSubjectId(bean.getId() + "");
        }
        if (isMine) {
            requestBody.setUserId(APP.getUserId());
            RequestUtil.createApi().selectMyBook(requestBody).compose(RequestUtil.<MyBookBean>handleResult())
                    .subscribe(new CommonsSubscriber<MyBookBean>() {
                        @Override
                        protected void onSuccess(MyBookBean bookBean) {
                            adapterMy.setNewData(bookBean.getRecords());
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

    public static BooKFragment newInstance(boolean param1, List<InitBookBean.SubjectsBean> param2) {
        BooKFragment fragment = new BooKFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, (Serializable)param2);
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
