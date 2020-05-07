package com.hym.errands.java;

import com.hym.errands.bmob.BBManager;
import com.hym.errands.bmob.MyUser;
import com.hym.errands.manager.UserManager;

import java.nio.channels.Pipe;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class login {

    private loginCallback mloginCallback;
    private String phone;

    public void checkPhoneCode(String phone,String code,loginCallback callback){
        this.mloginCallback=callback;
        this.phone=phone;
        BBManager.get().checkPhoneCode(phone, code, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    findUserByPhone();
                }else {
                    mloginCallback.error(e.getMessage(),e.getErrorCode());
                }
            }
        });
    }

    private void findUserByPhone() {
        BmobQuery<MyUser> query = new BmobQuery<>();
        query.addWhereEqualTo("phone",phone);
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                if (e==null) {
                    if (list.size()>0) {
                        MyUser myUser = list.get(0);
                        UserManager.get().saveUser(myUser);
                        mloginCallback.done(UserManager.get().getUser().getObjectId());
                    }else {
                        createUser();
                    }
                }else {
                    mloginCallback.error(e.getMessage(),e.getErrorCode());
                }
            }
        });
    }

    private void createUser() {
        MyUser user = new MyUser.Builder()
                .setName("hym")
                .setPayPwd(phone)
                .setPhone("null")
                .setPayPwd("0000")
                .setMoney(0.0)
                .setUpMoney(0.0)
                .build();
        user.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null) {
                    mloginCallback.done(UserManager.get().getUser().getObjectId());
                }else {
                    mloginCallback.error(e.getMessage(),e.getErrorCode());
                }
            }
        });
    }

    public interface loginCallback{
        void done(String id);
        void error(String errorMsg,int errorCode);
    }
}
