package com.yangqichao.bokuscience.business.ui.main;

import android.content.Context;
import android.content.Intent;

import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.LoginBean;
import com.yangqichao.bokuscience.business.ui.CommonWebViewActivity;
import com.yangqichao.bokuscience.business.ui.WWQKWebViewActivity;
import com.yangqichao.bokuscience.business.ui.book.BookMainActivity;
import com.yangqichao.bokuscience.business.ui.meetting.MeetingActivity;
import com.yangqichao.bokuscience.business.ui.share.ShareActivity;
import com.yangqichao.bokuscience.business.ui.video.VideoMainActivity;
import com.yangqichao.commonlib.util.PreferenceUtils;

/**
 * Created by yangqc on 2017/7/13.
 */

public class ShowMenuUtil {

//    {
//        "id": 2,
//            "code": "MK02",
//            "name": "扩展模块2",
//            "imgUrl": "2.jpg",
//            "contentUrl": "http://www.goole.com"
//    }, {
//        "id": 4,
//                "code": "hygl",
//                "name": "会议管理",
//                "imgUrl": "1.jpg",
//                "contentUrl": ""
//    }, {
//        "id": 5,
//                "code": "qksj",
//                "name": "期刊书籍",
//                "imgUrl": "2.jpg",
//                "contentUrl": ""
//    }, {
//        "id": 6,
//                "code": "gjgx",
//                "name": "共建共享",
//                "imgUrl": "2.jpg",
//                "contentUrl": ""
//    }, {
//        "id": 11,
//                "code": "yxsp",
//                "name": "医学视频",
//                "imgUrl": "2.jpg",
//                "contentUrl": ""
//    }

    public static int getImage(String code){
        if(code.equals("hygl")){
            return R.drawable.icon_hygl_big;
        }else if(code.equals("qksj")){
            return  R.drawable.icon_qksj_big;
        }else if(code.equals("gjgx")){
            return  R.drawable.icon_gjgx_big;
        }else if(code.equals("yxsp")){
            return   R.drawable.icon_yxsp_big;
        }else if(code.equals("wwqk")){
            return   R.drawable.icon_link_big;
        }else{
            return  R.drawable.icon_link_big;
        }
    }

    public static int getImageSmall(String code){
        if(code.equals("hygl")){
            return R.drawable.icon_hygl_small;
        }else if(code.equals("qksj")){
            return  R.drawable.icon_qksj_small;
        }else if(code.equals("gjgx")){
            return  R.drawable.icon_gjgx_small;
        }else if(code.equals("yxsp")){
            return   R.drawable.icon_yxsp_small;
        }else if(code.equals("wwqk")){
            return   R.drawable.icon_link_small;
        }else{
            return  R.drawable.icon_link_small;
        }
    }

    public static Intent getClass(Context context,LoginBean.ModuleDTOSBean bean){
        String code = bean.getCode();
        if(code.equals("hygl")){
            return new Intent(context,MeetingActivity.class);
        }else if(code.equals("qksj")){
            return new Intent(context,BookMainActivity.class);
        }else if(code.equals("gjgx")){
            return new Intent(context,ShareActivity.class);
        }else if(code.equals("yxsp")){
            return new Intent(context,VideoMainActivity.class);
        }else if(code.equals("wwqk")){
            Intent intent = new Intent(context,WWQKWebViewActivity.class);
            intent.putExtra("url", bean.getContentUrl());
            intent.putExtra("title", bean.getName());
            intent.putExtra("phone", PreferenceUtils.getPrefString(context,"phone",""));
            intent.putExtra("code", PreferenceUtils.getPrefString(context,"hospitalCode",""));
            return intent;
        }else{
            Intent intent = new Intent(context,CommonWebViewActivity.class);
            intent.putExtra("url", bean.getContentUrl());
            intent.putExtra("title", bean.getName());
            return intent;
        }
    }

    public static boolean isMainFuncation(String code){
        if("hygl".equals(code)||"qksj".equals(code)||"gjgx".equals(code)||"yxsp".equals(code)){
            return true;
        }else{
            return false;
        }
    };

}
