package com.library.base.liuxi.takeaway;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.github.markzhai.recyclerview.MultiTypeAdapter;
import com.library.base.liuxi.takeaway.bean.ItemPriceData;
import com.library.base.liuxi.takeaway.databinding.ActivityMainBinding;
import com.liuxi.baselibrary.base.BaseActivity;
import com.liuxi.baselibrary.utils.FormatUtil;
import com.liuxi.baselibrary.utils.PreferenceUtil;
import com.liuxi.baselibrary.utils.ToastUtil;
import com.liuxi.baselibrary.view.helper.ItemSpace;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {

    private final String PRE_NAME = "pre_name";

    private ActivityMainBinding mDataBinding;

    private PreferenceUtil mPreferenceUtil;
    private MultiTypeAdapter mTypeAdapter;
    private final int TYPE_EDIT = 0x01;
    private final int TYPE_SHOW = 0x02;
    private List<ItemPriceData> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = (ActivityMainBinding) initDataBinding(R.layout.activity_main);
        mDataBinding.setMain(this);
        mDataBinding.toolbar.setTitle("Takeaway");
        mDataBinding.toolbar.inflateMenu(R.menu.home_menu);
        mDataBinding.toolbar.setOnMenuItemClickListener(this);
        mPreferenceUtil = PreferenceUtil.getPreference(this, PreferenceUtil.COMMON_NAME);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mTypeAdapter = new MultiTypeAdapter(this);
        mTypeAdapter.addViewTypeToLayoutMap(TYPE_EDIT, R.layout.item_edit_type);
        mTypeAdapter.addViewTypeToLayoutMap(TYPE_SHOW, R.layout.item_show_type);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.recyclerView.addItemDecoration(new ItemSpace(this, 1));
        mDataBinding.recyclerView.setAdapter(mTypeAdapter);
        mDataList = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.confirm_btn) {
            int count = FormatUtil.string2int(mDataBinding.personNum.getText().toString().trim());
            if (count > 0) {
                mDataList.clear();
                for (int i = 0; i < count; i++) {
                    mDataList.add(new ItemPriceData());
                }
                mTypeAdapter.addAll(mDataList, TYPE_EDIT);
            }
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        final int id = item.getItemId();
        if (id == R.id.to_calculate) {
            toCalculate();

        } else if (id == R.id.to_reset) {
            mDataBinding.couponPrice.setText("");
            mDataBinding.payPrice.setText("");
            mDataBinding.shippingPrice.setText("");
            mDataBinding.personNum.setText("");
            mTypeAdapter.clear();

        } else if (id == R.id.to_add) {
            add();
        }
        return true;
    }

    private void toCalculate() {
        ToastUtil.showToast("To Calculate");
    }

    private void add() {
        final EditText et = new EditText(this);
        new AlertDialog.Builder(this).setTitle("添加用户名").setIcon(R.drawable.ic_person_add_black_36dp).setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String name = et.getText().toString();
                        if (FormatUtil.isEmpty(name)) {
                            ToastUtil.showToast("姓名不能为空");
                            return;
                        }
                        mPreferenceUtil.add(PRE_NAME, name);
                    }
                }).setNegativeButton("取消", null).show();
    }

}
