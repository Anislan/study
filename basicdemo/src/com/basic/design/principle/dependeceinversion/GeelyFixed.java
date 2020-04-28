package com.basic.design.principle.dependeceinversion;

public class GeelyFixed {

    // 构造器注入
    private ICourse iCourse;

    public GeelyFixed(ICourse iCourse){
        this.iCourse = iCourse;
    }

    // 接口注入方式
    public void studyCourese(){
        iCourse.studyCourse();
    }

    public GeelyFixed() {
    }

    // setter注入

    public void setiCourse(ICourse iCourse) {
        this.iCourse = iCourse;
    }
}
