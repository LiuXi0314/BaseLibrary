package com.liuxi.baselibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * TextView基类
 */

public class BaseTextView extends TextView {
    public BaseTextView(Context context) {
        super(context);
    }

    public BaseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
