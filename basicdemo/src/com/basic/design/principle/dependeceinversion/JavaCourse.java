package com.basic.design.principle.dependeceinversion;

public class JavaCourse implements ICourse {
    @Override
    public void studyCourse() {
        System.out.println("xxx在学习Java");
    }
}
