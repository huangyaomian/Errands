package com.hym.errands.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.hym.errands.BaseActivity;
import com.hym.errands.R;
import com.hym.errands.bmob.BBManager;
import com.hym.errands.bmob.MyUser;
import com.hym.errands.manager.UserManager;
import com.xuexiang.xui.widget.toast.XToast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class StatActivity extends BaseActivity {

    private Intent intent;
    private String id;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1000) {
                //下節視頻做
                intent.setClass(StatActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }else {
                intent.setClass(StatActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_srat);
        init();
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        id = sharedPreferences.getString("id", "");
        if (!TextUtils.isEmpty(id)){
            Log.e("StatActivity","id:" + id);
            findUser();
        }else {
            handler.sendEmptyMessageDelayed(1001,3000);
        }
    }

    private void findUser() {
        BBManager.get().findUser(id, new QueryListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e == null) {
                    UserManager.get().saveUser(myUser);
                    handler.sendEmptyMessageDelayed(1000,2000);
                }else {
                    XToast.warning(StatActivity.this,"查找用戶失敗"+ e.getErrorCode()).show();
                    Log.e("StatActivity","查找用戶失敗:" + e.getMessage());
                }
            }
        });
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
}
