package com.hym.errands.manager;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;

public class BDmanager {
    private ReverseGeoCodeOption mReverseGeoCodeOption;
    private GeoCoder mGeoCoder;

    public BDmanager(){
        mReverseGeoCodeOption = new ReverseGeoCodeOption();
        mGeoCoder = GeoCoder.newInstance();
    }

    private OnGetGeoCoderResultListener mOnGetGeoCoderResultListener;

    public void setmOnGetGeoCoderResultListener(OnGetGeoCoderResultListener mOnGetGeoCoderResultListener) {
        this.mOnGetGeoCoderResultListener = mOnGetGeoCoderResultListener;
    }

    public void setLatLng(LatLng latLng){
        mReverseGeoCodeOption.location(latLng);
        mGeoCoder.reverseGeoCode(mReverseGeoCodeOption);
        mGeoCoder.setOnGetGeoCodeResultListener(mOnGetGeoCoderResultListener);
    }
}
