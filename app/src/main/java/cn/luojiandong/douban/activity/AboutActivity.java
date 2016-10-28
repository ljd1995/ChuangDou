package cn.luojiandong.douban.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.luojiandong.douban.R;
import cn.luojiandong.douban.utils.UIUtils;

public class AboutActivity extends Activity {

    @BindView(R.id.about_title_bar_title)
    TextView mAboutTitleBarTitle;
    @BindView(R.id.tv_author)
    TextView mTvAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        mAboutTitleBarTitle.setText("关于");
        mTvAuthor.setText(UIUtils.getString(R.string.about_author)+"\n"+UIUtils.getString(R.string.about_author_line2));
    }

    @OnClick(R.id.about_titlebar_container)
    public void onClick() {
        finish();
    }


}
