package com.hym.errands.manager;

import com.hym.errands.bmob.MyUser;

public class UserManager {

    private static UserManager mUserManager;
    private UserManager(){};
    public static UserManager get(){
        if (mUserManager == null) {
            mUserManager = new UserManager();
        }
        return mUserManager;
    }

    private MyUser mMyUser;
    public void saveUser(MyUser user){
        this.mMyUser = user;
    }

    public MyUser getUser(){
        return mMyUser;
    }

    public void removeUser(){
        mMyUser = null;
    }
}
