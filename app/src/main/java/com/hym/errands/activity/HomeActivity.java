package com.hym.errands.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.hym.errands.BaseActivity;
import com.hym.errands.R;
import com.hym.errands.adpter.HomeBannerAdapter;
import com.hym.errands.bmob.MyUser;
import com.hym.errands.manager.UserManager;
import com.xuexiang.xui.widget.banner.recycler.BannerLayout;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.iv_photo_home)
    RadiusImageView mIvPhotoHome;
    @BindView(R.id.tv_username)
    TextView mTvUsername;
    @BindView(R.id.tv_userid)
    TextView mTvUserid;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.banner_home)
    BannerLayout mBannerHome;
    @BindView(R.id.tv_up_home)
    TextView mTvUpHome;
    @BindView(R.id.nes_home)
    View nesHome;

    private LinearLayout linTake;
    private ImageView mIvCloseHome;

    private HomeBannerAdapter adapter;
    private List<Integer> list;
    private BottomSheetBehavior<View> behavior;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();
        initData();

    }

    @Override
    public void init() {
        list = new ArrayList<>();
        intent = new Intent();
    }

    @Override
    public void initView() {
        behavior = BottomSheetBehavior.from(nesHome);
        behavior.setHideable(true);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        linTake = nesHome.findViewById(R.id.lin_take_home);
        mIvCloseHome = nesHome.findViewById(R.id.iv_close_home);

    }

    @Override
    public void initData() {
        MyUser user = UserManager.get().getUser();
        mTvUsername.setText(user.getName());
        mTvUserid.setText(user.getObjectId());
        mTvMoney.setText("当前剩余:" + user.getMoney());


        list.add(R.mipmap.ima_51);
        list.add(R.mipmap.ima_51);
        list.add(R.mipmap.ima_51);
        adapter = new HomeBannerAdapter(list);
        mBannerHome.setAdapter(adapter);
    }

    @Override
    public void initEvent() {

    }

    @OnClick({R.id.iv_photo_home, R.id.tv_username, R.id.tv_userid, R.id.tv_money, R.id.banner_home, R.id.tv_up_home, R.id.iv_close_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_photo_home:
                break;
            case R.id.tv_username:
                break;
            case R.id.tv_userid:
                break;
            case R.id.tv_money:
                break;
            case R.id.banner_home:
                break;
            case R.id.tv_up_home:
                if (behavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
            case R.id.iv_close_home:
                if (behavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
                break;
            case R.id.lin_take_home:
                intent.setClass(HomeActivity.this,TakeActivity.class);
                startActivity(intent);
                break;

        }
    }
}
