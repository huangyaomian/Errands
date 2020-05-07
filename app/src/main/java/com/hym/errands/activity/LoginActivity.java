package com.hym.errands.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.hym.errands.BaseActivity;
import com.hym.errands.R;
import com.hym.errands.bmob.BBManager;
import com.hym.errands.manager.UserManager;
import com.xuexiang.xui.widget.toast.XToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.iv_close_login)
    ImageView mIvCloseLogin;
    @BindView(R.id.et_phone_login)
    EditText mEtPhoneLogin;
    @BindView(R.id.iv_send_login)
    ImageView mIvSendLogin;

    private String phone;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
        /*// 动态获取权限，Android 6.0+ 新特性，
        // 一些保护权限，除了要在AndroidManifest中声明权限，还要使用如下代码动态获取
        if (Build.VERSION.SDK_INT >= 23){
            int REQUEST_CODE_CONTACT = 101;

            String[] permissions = {Manifest.permission.INTERNET};

            // 验证是否许可权限
            for (String str : permissions){
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED){
                    // 申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    break;
                }
            }
        }*/
    }

    @Override
    public void init() {
        intent = new Intent();
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


    @OnClick({R.id.iv_close_login, R.id.iv_send_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close_login:
                finish();
                break;
            case R.id.iv_send_login:
                sendCode();
                break;
        }
    }


    private void sendCode() {
        phone = mEtPhoneLogin.getText().toString();
        if (phone.length() != 11) {
            XToast.warning(this,"手機號不等於11位").show();
        }
        BBManager.get().sendCode(phone, new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {
                    intent.setClass(LoginActivity.this,CheckActivity.class);
                    intent.putExtra("phone",phone);
                    startActivityForResult(intent,1000);
                }else {
                    XToast.warning(LoginActivity.this,"發送失敗：" +e.getErrorCode()+ "-" + e.getMessage() + "\n").show();
                    Log.e("LoginActivity","发送失败错误信息：" + e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            intent.setClass(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
            Log.e("LoginActivity", "userid"+UserManager.get().getUser().getObjectId());
        }
    }
}
