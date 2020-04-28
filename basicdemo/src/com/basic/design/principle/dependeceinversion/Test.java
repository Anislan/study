package com.basic.design.principle.dependeceinversion;

/**
 *  应用层函数依赖Geely，高层模块
 */
public class Test {

    public static void main(String[] args) {
        // v1
//        Geely geely = new Geely();
//        geely.studyFECourse();
//        geely.studyJavaCourse();

        // v2 接口注入
//        GeelyFixed geelyFixed = new GeelyFixed();
//        geelyFixed.studyCourese(new JavaCourse());
//        geelyFixed.studyCourese(new FECourse());

        // v3构造器注入
//        GeelyFixed geelyFixed = new GeelyFixed(new JavaCourse());
//        geelyFixed.studyCourese();

        // v4 Setter注入
        GeelyFixed geelyFixed = new GeelyFixed();
        geelyFixed.setiCourse(new JavaCourse());
        geelyFixed.studyCourese();

        geelyFixed.setiCourse(new FECourse());
        geelyFixed.studyCourese();
    }
}
