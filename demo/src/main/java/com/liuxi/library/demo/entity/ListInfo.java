package com.liuxi.library.demo.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by david on 17-5-2.
 */

public class ListInfo extends BaseObservable {

    private String name;
    private String introduction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
