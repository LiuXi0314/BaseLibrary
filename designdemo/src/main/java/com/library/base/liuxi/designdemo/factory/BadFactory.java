package com.library.base.liuxi.designdemo.factory;

/**
 * Created by david on 17-6-2.
 */

public class BadFactory implements  StudentFactory{
    @Override
    public Student getStudent() {
        return new BadStudent();
    }
}
