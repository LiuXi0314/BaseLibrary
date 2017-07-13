package com.liuxi.library.demo.Activity;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.liuxi.baselibrary.base.BaseActivity;
import com.liuxi.library.demo.R;
import com.liuxi.library.demo.databinding.ActivityDataBindingBinding;
import com.liuxi.library.demo.entity.User;

public class DataBindingActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        User user = new User();
        user.setName("炫酷的MVVM");
        user.setId(1);
        dataBinding.setUser(user);
        dataBinding.setMain(DataBindingActivity.this);
        initToolBar("DataBind Demo", true);
    }


    @Override
    public void onClick(View v) {

    }
}
