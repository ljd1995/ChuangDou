package cn.luojiandong.douban.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.luojiandong.douban.R;
import cn.luojiandong.douban.bean.FeedbackBean;
import cn.luojiandong.douban.utils.UIUtils;

public class FeedBackActivity extends Activity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    @BindView(R.id.rb_advice)
    RadioButton mRbAdvice;
    @BindView(R.id.rb_consult)
    RadioButton mRbConsult;
    @BindView(R.id.rb_complain)
    RadioButton mRbComplain;
    @BindView(R.id.feed_radioGroup)
    RadioGroup mFeedRadioGroup;
    @BindView(R.id.btn_feedback_up)
    Button mBtnFeedbackUpload;
    @BindView(R.id.et_feedback)
    EditText mEtFeedback;
    @BindView(R.id.et_phone_or_email)
    EditText mEtPhoneOrEmail;
    private String mPhoneOrEmail;
    private String mFeedbackInfo;
    private String mFeedbackType = UIUtils.getString(R.string.rb_advice);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);
        mRbAdvice.setChecked(true);
        initData();
    }

    private void initData() {
        mFeedRadioGroup.setOnCheckedChangeListener(this);
        mBtnFeedbackUpload.setOnClickListener(this);
    }

    @OnClick(R.id.feedback_titlebar_container)
    public void onBackClick() {
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_advice:
                mRbConsult.setChecked(false);
                mRbComplain.setChecked(false);
                break;
            case R.id.rb_consult:
                mRbAdvice.setChecked(false);
                mRbComplain.setChecked(false);
                mFeedbackType = mRbConsult.getText().toString();
                break;
            case R.id.rb_complain:
                mRbConsult.setChecked(false);
                mRbAdvice.setChecked(false);
                mFeedbackType = mRbComplain.getText().toString();
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        mFeedbackInfo = mEtFeedback.getText().toString();
        mPhoneOrEmail = mEtPhoneOrEmail.getText().toString().trim();
        FeedbackBean bean = new FeedbackBean();
        if (!TextUtils.isEmpty(mFeedbackInfo) || !TextUtils.isEmpty(mPhoneOrEmail)) {
            bean.setFeedbackCall(mPhoneOrEmail);
            bean.setFeedbackInfo(mFeedbackInfo);
            bean.setFeedbackType(mFeedbackType);
            bean.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        Toast.makeText(FeedBackActivity.this, "反馈提交成功，感谢您的反馈，祝你有个好心情！", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(FeedBackActivity.this, "反馈提交失败" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else{
            Toast.makeText(FeedBackActivity.this, "反馈内容不能为空哦", Toast.LENGTH_LONG).show();
        }
    }
}
