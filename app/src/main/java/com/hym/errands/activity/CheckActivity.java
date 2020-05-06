package com.hym.errands.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.material.button.MaterialButton;
import com.hym.errands.BaseActivity;
import com.hym.errands.R;
import com.hym.errands.bmob.BBManager;
import com.xuexiang.xui.widget.edittext.verify.VerifyCodeEditText;
import com.xuexiang.xui.widget.toast.XToast;

import java.util.logging.LoggingMXBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class CheckActivity extends BaseActivity {

    @BindView(R.id.iv_close_check)
    ImageView mIvCloseCheck;
    @BindView(R.id.tv_phone_check)
    TextView mTvPhoneCheck;
    @BindView(R.id.iv_up_check)
    ImageView mIvUpCheck;
    @BindView(R.id.et_code_check)
    VerifyCodeEditText mEtCodeCheck;
    @BindView(R.id.btn_time)
    MaterialButton mBtnTime;

    private String phone;
    private int count = 60;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                mBtnTime.setEnabled(true);
                mBtnTime.setText("重新發送");
                mBtnTime.setBackgroundColor(ActivityCompat.getColor(CheckActivity.this,R.color.main_btn));
            }else {
                count--;
                mBtnTime.setText(count + "s");
                handler.sendEmptyMessageDelayed(count,1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        ButterKnife.bind(this);
        initData();
        initEvent();
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        mTvPhoneCheck.setText(phone);
    }

    @Override
    public void initEvent() {
        mEtCodeCheck.setOnInputListener(new VerifyCodeEditText.OnInputListener() {
            @Override
            public void onComplete(String input) {

            }

            @Override
            public void onChange(String input) {

            }

            @Override
            public void onClear() {

            }
        });
    }

    @OnClick({R.id.iv_close_check, R.id.tv_phone_check, R.id.iv_up_check, R.id.btn_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close_check:
                finish();
                break;
            case R.id.tv_phone_check:
                finish();
                break;
            case R.id.iv_up_check:
                break;
            case R.id.btn_time:
                sendCode();
                break;
        }
    }

    private void sendCode() {
        BBManager.get().sendCode(phone, new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {
                    XToast.success(CheckActivity.this,"發送成功").show();
                }else {
                    XToast.warning(CheckActivity.this,"發送失敗：" + e.getErrorCode()).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessage(count);
    }
}
