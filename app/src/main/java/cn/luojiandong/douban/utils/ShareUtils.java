package cn.luojiandong.douban.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by JackLuo on 2016/10/22.
 *
 * @description
 */

public class ShareUtils {
    public static void getShare(Context context, double mRating, String mTitle, String mMoblieUrl) {
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
        share_intent.setType("text/plain");//设置分享内容的类型
        share_intent.putExtra(Intent.EXTRA_SUBJECT, "电影分享");//添加分享内容标题
        share_intent.putExtra(Intent.EXTRA_TEXT, "推荐你一部电影，豆瓣评分" + mRating + "分\n" + mTitle + "\n" + mMoblieUrl);//添加分享内容
        //创建分享的Dialog
        share_intent = Intent.createChooser(share_intent, "分享到");
        context.startActivity(share_intent);
    }

}
