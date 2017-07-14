package com.library.base.liuxi.designdemo.factory;

/**
 * Created by david on 17-6-2.
 */

public class MainClass {

    public static void main(String[] args) {
        studyGood();
        studyBad();
    }

    private static void studyGood() {
        StudentFactory factory = new GoodFactory();
        Student student = factory.getStudent();
        student.studyEnglish();
        student.studyLanguage();
        student.studyMath();
    }

    private static void studyBad() {
        StudentFactory factory = new BadFactory();
        Student student = factory.getStudent();
        student.studyEnglish();
        student.studyLanguage();
        student.studyMath();

    }
}
