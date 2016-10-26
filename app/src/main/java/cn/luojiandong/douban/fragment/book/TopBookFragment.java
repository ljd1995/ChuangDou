package cn.luojiandong.douban.fragment.book;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.luojiandong.douban.R;
import cn.luojiandong.douban.activity.BookMoreActivity;
import cn.luojiandong.douban.base.BaseFragment;
import cn.luojiandong.douban.controller.BookClassicsController;
import cn.luojiandong.douban.controller.BookCultureController;
import cn.luojiandong.douban.controller.BookLifeController;
import cn.luojiandong.douban.controller.BookLiteratureController;
import cn.luojiandong.douban.controller.BookPopularController;

/**
 * Created by 95250 on 2016/10/18.
 */
public class TopBookFragment extends BaseFragment {

    @BindView(R.id.book_list_item1)
    View bookListClassics;
    @BindView(R.id.book_list_item2)
    View bookListLiterature;
    @BindView(R.id.book_list_item3)
    View bookListPopular;
    @BindView(R.id.book_list_item4)
    View bookListCulture;
    @BindView(R.id.book_list_item5)
    View bookListLife;
    @BindView(R.id.topbook_pager_loading)
    View mPagerLoading;
    @BindView(R.id.topbook_pager_error)
    View mPagerError;
    @BindView(R.id.ll_topbook_container)
    LinearLayout mTopbookContainer;
    private RelativeLayout mClassicsMoreContainer;
    private RelativeLayout mLiteratureMoreContainer;
    private RelativeLayout mPopularMoreContainer;
    private RelativeLayout mCultureMoreContainer;
    private RelativeLayout mLifeMoreContainer;

    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_top_book, null, false);
        ButterKnife.bind(this, view);
        mTopbookContainer.setVisibility(View.GONE);
        mPagerError.setVisibility(View.GONE);
        mClassicsMoreContainer = (RelativeLayout) bookListClassics.findViewById(R.id.book_more_container);
        mLiteratureMoreContainer = (RelativeLayout) bookListLiterature.findViewById(R.id.book_more_container);
        mPopularMoreContainer = (RelativeLayout) bookListPopular.findViewById(R.id.book_more_container);
        mCultureMoreContainer = (RelativeLayout) bookListCulture.findViewById(R.id.book_more_container);
        mLifeMoreContainer = (RelativeLayout) bookListLife.findViewById(R.id.book_more_container);
        initListener();
        return view;
    }

    /**
     * 由于布局是include进来的，id一样，无法简化代码
     */
    public void initListener() {
        final Intent intent = new Intent(getActivity(), BookMoreActivity.class);
        mClassicsMoreContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("position", 0);
                startActivity(intent);
            }
        });
        mLiteratureMoreContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("position", 1);
                startActivity(intent);
            }
        });
        mPopularMoreContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("position", 2);
                startActivity(intent);
            }
        });
        mCultureMoreContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("position", 3);
                startActivity(intent);
            }
        });
        mLifeMoreContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("position", 4);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        BookClassicsController classicsController = new BookClassicsController(getActivity(), bookListClassics, null, mPagerLoading, mPagerError, mTopbookContainer);
        BookLiteratureController literatureController = new BookLiteratureController(getActivity(), bookListLiterature, null, mPagerLoading, mPagerError, mTopbookContainer);
        BookPopularController popularController = new BookPopularController(getActivity(), bookListPopular, null, mPagerLoading, mPagerError, mTopbookContainer);
        BookCultureController cultureController = new BookCultureController(getActivity(), bookListCulture, null, mPagerLoading, mPagerError, mTopbookContainer);
        BookLifeController lifeController = new BookLifeController(getActivity(), bookListLife, null, mPagerLoading, mPagerError, mTopbookContainer);
        classicsController.requestAndSetData();
        literatureController.requestAndSetData();
        popularController.requestAndSetData();
        cultureController.requestAndSetData();
        lifeController.requestAndSetData();
    }

}
