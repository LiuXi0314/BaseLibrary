package com.liuxi.library.demo.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.liuxi.baselibrary.base.BaseActivity;
import com.liuxi.library.demo.R;
import com.liuxi.library.demo.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        mainBinding.setMain(MainActivity.this);
        initToolBar("Toolbar", false);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.data_binding) {
            startActivity(new Intent(MainActivity.this, DataBindingActivity.class));
        } else if (id == R.id.recyclerView) {
            startActivity(new Intent(MainActivity.this, RecyclerViewBindingActivity.class));
        }
    }

}
