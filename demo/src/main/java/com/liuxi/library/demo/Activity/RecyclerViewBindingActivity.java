package com.liuxi.library.demo.Activity;

import android.databinding.DataBindingUtil;
import android.databinding.tool.reflection.TypeUtil;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.github.markzhai.recyclerview.BaseViewAdapter;
import com.github.markzhai.recyclerview.BindingViewHolder;
import com.github.markzhai.recyclerview.MultiTypeAdapter;
import com.github.markzhai.recyclerview.SingleTypeAdapter;
import com.github.markzhai.recyclerview.databinding.ItemSingleTypeBinding;
import com.liuxi.baselibrary.base.BaseActivity;
import com.liuxi.baselibrary.utils.FormatUtil;
import com.liuxi.baselibrary.utils.LogUtil;
import com.liuxi.baselibrary.utils.ToastUtil;
import com.liuxi.library.demo.R;
import com.liuxi.library.demo.databinding.ActivityBindingRecyclerBinding;
import com.liuxi.library.demo.entity.ListInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 17-5-2.
 */

public class RecyclerViewBindingActivity extends BaseActivity {

    private SingleTypeAdapter<ListInfo> mSingelAdapter;
    private MultiTypeAdapter mMulAdapter;
    private static final int TYPE_TITLE = 0;
    private static final int TYPE_CONTENT = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBindingRecyclerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_binding_recycler);
        initToolBar("RecyclerViewDemo", true);
        binding.setMain(RecyclerViewBindingActivity.this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(new ItemSpace());
        initSingleAdapter();
        initMulAdapter();
        binding.recyclerView.setAdapter(mMulAdapter);
    }

    private void initSingleAdapter() {
        mSingelAdapter = new SingleTypeAdapter<>(this, R.layout.recycler_view_item_layout);
        mSingelAdapter.setPresenter(new Presenter());
        mSingelAdapter.set(getData());
        mSingelAdapter.setDecorator(new DemoAdapterDecorator());
    }


    private void initMulAdapter() {
        mMulAdapter = new MultiTypeAdapter(this);
        mMulAdapter.setPresenter(new Presenter());
        mMulAdapter.addViewTypeToLayoutMap(TYPE_TITLE, R.layout.recycler_view_item_layout);
        mMulAdapter.addViewTypeToLayoutMap(TYPE_CONTENT, R.layout.recycler_view_item_layout);
        mMulAdapter.add(null, TYPE_TITLE);
        mMulAdapter.addAll(getData(), TYPE_CONTENT);
    }

    private List<ListInfo> getData() {
        List<ListInfo> mInfoList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ListInfo info = new ListInfo();
            info.setIntroduction("双击666");
            info.setName("Lanna del Rey " + i);
            mInfoList.add(info);
        }
        return mInfoList;
    }


    @Override
    public void onClick(View v) {

    }


    public class Presenter implements SingleTypeAdapter.Presenter<ListInfo> {

        @Override
        public void onItemClick(ListInfo listInfo) {
            ToastUtil.showToast("还是好好");
        }
    }

    public class DemoAdapterDecorator implements BaseViewAdapter.Decorator {

        @Override
        public void decorator(BindingViewHolder holder, int position, int viewType) {
            // you may do something according to position or view type
            ItemSingleTypeBinding binding = (ItemSingleTypeBinding) holder.getBinding();


        }
    }

    class ItemSpace extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (parent.getChildLayoutPosition(view) > 0) {
                outRect.top = FormatUtil.dp2px(RecyclerViewBindingActivity.this, 1);
            }
        }

    }

}
