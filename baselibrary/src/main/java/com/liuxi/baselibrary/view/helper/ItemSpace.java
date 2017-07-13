package com.liuxi.baselibrary.view.helper;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liuxi.baselibrary.utils.FormatUtil;

/**
 * Created by david on 17-5-15.
 */

public class ItemSpace extends RecyclerView.ItemDecoration {

    private int space;

    public ItemSpace(Context context, int space) {
        this.space = FormatUtil.dp2px(context, space);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildLayoutPosition(view) > 0) {
            outRect.top = space;
        }
    }
}
