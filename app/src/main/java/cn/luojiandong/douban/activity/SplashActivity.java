package cn.luojiandong.douban.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.luojiandong.douban.R;
import cn.luojiandong.douban.constants.MyConstants;
import cn.luojiandong.douban.utils.GlideUtils;

public class SplashActivity extends Activity {

    @BindView(R.id.iv_splash)
    ImageView mSplashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        GlideUtils.loadImageViewAnim(this, MyConstants.bingImageApi, R.anim.zoomin, mSplashImage);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };
        timer.schedule(timerTask, 1500);
    }
}
