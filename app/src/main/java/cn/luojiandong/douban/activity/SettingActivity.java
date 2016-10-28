package cn.luojiandong.douban.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.luojiandong.douban.R;
import cn.luojiandong.douban.utils.GlideUtils;

public class SettingActivity extends Activity {

    @BindView(R.id.tv_cache_size)
    TextView mTvCacheSize;
    @BindView(R.id.setting_title_bar_title)
    TextView mSettingTitleBarTitle;
    @BindView(R.id.rl_cache_container)
    RelativeLayout mRlCacheContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        mSettingTitleBarTitle.setText("设置");
        initData();
    }

    private void initData() {
        String cacheSize = GlideUtils.getCacheSize(this);
        mTvCacheSize.setText(cacheSize);
    }

    @OnClick(R.id.setting_titlebar_container)
    public void onBackClick() {
        finish();
    }

    @OnClick(R.id.rl_cache_container)
    public void onCacheClick() {
        showDialog();
    }

    public void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle("警告")
                .setMessage("是否删除全部图片缓存，这将会导致下次启动App时重新加载图片，耗费您更多流量！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SettingActivity.this, "正在清除缓存", Toast.LENGTH_SHORT).show();
                        GlideUtils.clearImageAllCache(SettingActivity.this);
                        initData();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();

    }
}
