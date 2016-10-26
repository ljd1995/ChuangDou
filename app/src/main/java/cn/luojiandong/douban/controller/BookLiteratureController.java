package cn.luojiandong.douban.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import cn.luojiandong.douban.base.BaseBookDataController;

/**
 * 项目名：Douban
 * 包名：cn.luojiandong.douban.http
 * 文件名: BookClassicsController
 * 创建者: JackLuo
 * 创建时间: 2016-10-23 18:25
 * 描述：
 */
public  class BookLiteratureController extends BaseBookDataController {

    public BookLiteratureController(Context context, View view, RecyclerView recyclerView, View pageLoading, View pageError, ViewGroup container) {
        super(context, view, recyclerView, pageLoading, pageError, container);
    }

    @Override
    public int getBookType() {
        return 1;
    }
}
