package com.library.base.liuxi.takeaway.bean;

import com.liuxi.baselibrary.base.BaseApplication;
import com.liuxi.baselibrary.utils.PreferenceUtil;
import com.liuxi.baselibrary.utils.UrlUtils;

/**
 * 单人价格
 */

public class ItemPriceData {

    private String itemName;
    private double itemPrice;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemName(){


    }
}
