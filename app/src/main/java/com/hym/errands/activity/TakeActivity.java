package com.hym.errands.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.hym.errands.BaseActivity;
import com.hym.errands.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TakeActivity extends BaseActivity {

    @BindView(R.id.mapView)
    MapView mapView;

    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take);
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
        mBaiduMap = mapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(this);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("ba0911");
        option.setScanSpan(0);
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        //option.setLocationNotify(true);
        //可选，默认false，设置是否开启Gps定位
        option.setOpenGps(true);

        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(new MyLocationListener());
        mLocationClient.start();

    }

    private class MyLocationListener extends BDAbstractLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation == null) {
                return;
            }
            Log.e("TakeActivity","当前地址" + bdLocation.getAddrStr());
            MyLocationData myLocationData = new MyLocationData.Builder()
                    .accuracy(0)
                    .direction(100f)
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude())
                    .build();
            mBaiduMap.setMyLocationData(myLocationData);
            if (isFirst) {
                LatLng latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(latLng);
                MapStatusUpdate update1 = MapStatusUpdateFactory.zoomTo(18f);
                mBaiduMap.setMapStatus(update);
                mBaiduMap.animateMapStatus(update1);
                isFirst = false;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mBaiduMap = null;
    }

    @OnClick(R.id.mapView)
    public void onViewClicked() {
    }
}
