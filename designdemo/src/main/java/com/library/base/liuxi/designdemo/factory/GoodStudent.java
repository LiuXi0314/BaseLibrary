package com.library.base.liuxi.designdemo.factory;

/**
 * Created by david on 17-6-2.
 */

public class GoodStudent extends Student{


    @Override
    public void studyMath() {
        super.studyMath();
        System.out.println("数学成绩好");
    }

    @Override
    public void studyLanguage() {
        super.studyLanguage();
        System.out.println("语文成绩好");
    }

    @Override
    public void studyEnglish() {
        super.studyEnglish();
        System.out.println("英语成绩好");
    }
}
