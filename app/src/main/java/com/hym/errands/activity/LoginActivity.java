package com.hym.errands.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.hym.errands.BaseActivity;
import com.hym.errands.R;
import com.xuexiang.xui.widget.toast.XToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.iv_close_login)
    ImageView mIvCloseLogin;
    @BindView(R.id.et_phone_login)
    EditText mEtPhoneLogin;
    @BindView(R.id.iv_send_login)
    ImageView mIvSendLogin;

    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @OnClick({R.id.iv_close_login, R.id.et_phone_login, R.id.iv_send_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close_login:
                finish();
                break;
            case R.id.et_phone_login:
                sendCode();
                break;
            case R.id.iv_send_login:
                break;
        }
    }

    private void sendCode() {
        phone = mEtPhoneLogin.getText().toString();
        if (phone.length() != 11) {
            XToast.warning(this,"手機號不等於11位").show();
        }
    }
}
