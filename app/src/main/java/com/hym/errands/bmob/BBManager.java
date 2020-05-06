package com.hym.errands.bmob;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class BBManager {
    private static BBManager mBBManager;
    private BBManager(){}

    public static BBManager get(){
        if (mBBManager == null){
            synchronized (BBManager.class){
                if (mBBManager == null){
                    mBBManager = new BBManager();
                }
            }
        }

        return mBBManager;
    }

    /**
     * 通过id 查询用户
     *
     *
     * @param id       id
     * @param listener 结果回调
     */
    public void findUser(String id,QueryListener<MyUser> listener){
        BmobQuery<MyUser> query = new BmobQuery<>();
        query.getObject(id,listener);
    }

    /**
     * 发送验证码
     *
     * @param phone    手机号
     * @param listener 结果回调
     */
    public void sendCode(String phone, QueryListener<Integer> listener){
        BmobSMS.requestSMSCode(phone,"",listener);
    }

    /**
     *
     * 验证验证码
     *
     * @param phone    手机号
     * @param code     验证码
     * @param listener 结果回调
     */
    public void checkPhoneCode(String phone, String code, UpdateListener listener){
        BmobSMS.verifySmsCode(phone,code,listener);
    }
}
