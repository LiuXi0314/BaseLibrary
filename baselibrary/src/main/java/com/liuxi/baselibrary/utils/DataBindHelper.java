package com.liuxi.baselibrary.utils;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

/**
 * Created by david on 17-5-12.
 *
 */

public class DataBindHelper {

    private Activity mActivity;
    private ViewDataBinding mDataBinding;

    public DataBindHelper(Activity activity,int layoutId) {
        mActivity = activity;
        mDataBinding = DataBindingUtil.setContentView(mActivity,layoutId);
    }

    public ViewDataBinding getInstance() {
        return mDataBinding;
    }


}
