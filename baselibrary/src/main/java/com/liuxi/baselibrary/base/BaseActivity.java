package com.liuxi.baselibrary.base;

import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.liuxi.baselibrary.R;
import com.liuxi.baselibrary.utils.ActivityUtil;
import com.liuxi.baselibrary.utils.DeviceUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Activity基类
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener, BaseActivityLifecycleCallbacks.OnForegroundChangedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        BaseActivityLifecycleCallbacks.getInstance().addOnForegroundChangedListener(this);
        ActivityUtil.getInstance().addActivity(this);
        //设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH && !DeviceUtils.isMiUi()) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorWhite));
        }
        if (DeviceUtils.isMiUi()) {
            setStatusBarDarkMode(true);
        }

    }

    /**
     * 加载 toolbar基本属性
     *
     * @param title
     */
    protected void initToolBar(String title, boolean hasBackIcon) {
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle(title);
    }

    /**
     *
     */
    protected ViewDataBinding initDataBinding(int layoutId) {
        return DataBindingUtil.setContentView(this, layoutId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtil.getInstance().removeActivity(this);
        BaseActivityLifecycleCallbacks.getInstance().removeOnForegroundChangedListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String msg) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onClick(View v) {

    }


    @Override
    public void toForeground() {

    }

    @Override
    public void toBackground() {

    }

    @TargetApi(21)
    public void setStatusBarDarkMode(boolean darkmode) {
        Class<? extends Window> clazz = getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
