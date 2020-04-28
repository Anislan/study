package com.basic.design.principle.dependeceinversion;

public class FECourse implements ICourse {
    @Override
    public void studyCourse() {
        System.out.println("xxx在学习FE");
    }
}
