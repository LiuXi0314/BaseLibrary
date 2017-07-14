package com.library.base.liuxi.takeaway;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.github.markzhai.recyclerview.SingleTypeAdapter;
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
    private String formatString = "%s   原价： %.2f  实际支付： %.2f";

    private ActivityMainBinding mDataBinding;
    private PreferenceUtil mPreferenceUtil;
    private SingleTypeAdapter<ItemPriceData> mAdapter;
    private List<String> mNameList;
    private List<String> mTmpList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = (ActivityMainBinding) initDataBinding(R.layout.activity_main);
        mDataBinding.setMain(this);
        mDataBinding.toolbar.setTitle(getString(R.string.app_name));
        mDataBinding.toolbar.inflateMenu(R.menu.home_menu);
        mDataBinding.toolbar.setOnMenuItemClickListener(this);
        mPreferenceUtil = PreferenceUtil.getPreference(this, PreferenceUtil.COMMON_NAME);
        mNameList = FormatUtil.string2List(mPreferenceUtil.get(PRE_NAME, ""));
        if (mNameList == null) {
            mNameList = new ArrayList<>();
        }
        mTmpList = new ArrayList<>();
        mTmpList.addAll(mNameList);
        InputFilter[] filter = {new CashierInputFilter()};
        mDataBinding.couponPrice.setFilters(filter);
        mDataBinding.shippingPrice.setFilters(filter);
        mDataBinding.payPrice.setFilters(filter);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new SingleTypeAdapter(this, R.layout.item_show_type);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.recyclerView.addItemDecoration(new ItemSpace(this, 1));
        mDataBinding.recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.to_add_person) {
            addPersonView();
        } else if (id == R.id.to_calculate) {
            toCalculate();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        final int id = item.getItemId();
        if (id == R.id.to_reset) {
            mDataBinding.couponPrice.setText("");
            mDataBinding.payPrice.setText("");
            mDataBinding.shippingPrice.setText("");
            mDataBinding.addPersonView.removeAllViews();
            mDataBinding.toAddPerson.setVisibility(View.VISIBLE);
            mDataBinding.toCalculate.setVisibility(View.VISIBLE);
            mAdapter.clear();
        } else if (id == R.id.to_add) {
            add();
        }
        return true;
    }

    /**
     * 计算
     */
    private void toCalculate() {
        if (mDataBinding.payPrice.getText().length() <= 0) {
            return;
        }
        if (mDataBinding.shippingPrice.getText().length() <= 0) {
            return;
        }
        if (mDataBinding.couponPrice.getText().length() <= 0) {
            return;
        }
        List<ItemPriceData> mDataList = mDataBinding.addPersonView.getPriceList();
        if (mDataList == null || mDataList.isEmpty()) return;

        double payPrice = Double.parseDouble(mDataBinding.payPrice.getText().toString().trim());
        double couponPrice = Double.parseDouble(mDataBinding.couponPrice.getText().toString().trim());
        double shippingPrice = Double.parseDouble(mDataBinding.shippingPrice.getText().toString().trim());
        double allPrice = 0;
        for (int i = 0; i < mDataList.size(); i++) {
            double oldPrice = mDataList.get(i).getItemPrice();
            double newPrice = oldPrice * (payPrice - shippingPrice) / (payPrice + couponPrice - shippingPrice) + shippingPrice / (mDataBinding.addPersonView.getChildCount());
            String s = String.format(formatString, mDataList.get(i).getItemName(), oldPrice, newPrice);
            mDataList.get(i).setItemPriceString(s);
            allPrice += newPrice;
        }
        ItemPriceData allData = new ItemPriceData();
        allData.setItemPriceString(String.format("总价格： %.2f", allPrice));
        mDataList.add(allData);
        mAdapter.clear();
        mAdapter.set(mDataList);
        mDataBinding.addPersonView.removeAllViews();
        mDataBinding.toAddPerson.setVisibility(View.GONE);
        mDataBinding.toCalculate.setVisibility(View.GONE);
    }

    /**
     * 添加用户
     */
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
                        mNameList.add(name);
                        mTmpList.add(name);
                        mPreferenceUtil.add(PRE_NAME, FormatUtil.list2String(mNameList));
                    }
                }).setNegativeButton("取消", null).show();
    }

    private void addPersonView() {
        if (mTmpList == null || mTmpList.isEmpty()) {
            ToastUtil.showToast("请先添加订饭人姓名~");
            return;
        }
        final String[] names = mTmpList.toArray(new String[mTmpList.size()]);
        new AlertDialog.Builder(this).setTitle("选择用户")
                .setItems(names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int position) {
                        String name = names[position];
                        ItemPriceData data = new ItemPriceData(name);
                        mDataBinding.addPersonView.addChildView(data);
                        mTmpList.remove(position);
                    }
                }).setNegativeButton("取消", null).show();
    }

}
