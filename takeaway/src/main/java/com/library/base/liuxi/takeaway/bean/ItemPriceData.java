package com.library.base.liuxi.takeaway.bean;

import android.databinding.BaseObservable;

/**
 * 单人价格
 */

public class ItemPriceData extends BaseObservable {

    private String itemName;
    private double itemPrice;
    private String itemPriceString;

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

    public String getItemPriceString() {
        return itemPriceString;
    }

    public void setItemPriceString(String itemPriceString) {
        this.itemPriceString = itemPriceString;
    }

    public ItemPriceData(String itemName) {
        this.itemName = itemName;
    }

    public ItemPriceData() {
    }
}
