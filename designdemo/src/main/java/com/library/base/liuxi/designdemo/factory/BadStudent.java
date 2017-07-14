package com.library.base.liuxi.designdemo.factory;

/**
 * Created by david on 17-6-2.
 */

public class BadStudent extends Student {
    @Override
    public void studyMath() {
        super.studyMath();
        System.out.println("数学学不好");
    }

    @Override
    public void studyLanguage() {
        super.studyLanguage();
        System.out.println("语文学不好");
    }

    @Override
    public void studyEnglish() {
        super.studyEnglish();
        System.out.println("英语学不好");
    }
}
