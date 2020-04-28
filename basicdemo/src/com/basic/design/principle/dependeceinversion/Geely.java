package com.basic.design.principle.dependeceinversion;

/**
 *  实现类,经常修改,底层次模块
 */
public class Geely {

    public void studyJavaCourse(){
        System.out.println("xxx在学习Java课程");
    }

    public void studyFECourse(){
        System.out.println("xxx在学习FE课程");
    }

    // 新增Python课程
    public void studyPthonCourse(){
        System.out.println("xxx在学习Python课程");
    }
}
