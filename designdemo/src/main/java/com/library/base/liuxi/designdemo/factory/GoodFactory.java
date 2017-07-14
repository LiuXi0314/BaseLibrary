package com.library.base.liuxi.designdemo.factory;

/**
 * Created by david on 17-6-2.
 */

public class GoodFactory implements StudentFactory {
    @Override
    public Student getStudent() {
        return new GoodStudent();
    }
}
