package com.basic.design.principle.openclose;

public class Test {
    public static void main(String[] args) {
//        ICourse iCourse = new JavaCourse(96,"Java从零到企业级电商开发",345d);
        // 变化后
        ICourse iCourse = new JavaDiscountCourse(96,"Java从零到企业级电商开发",345d);
        JavaDiscountCourse javaDiscountCourse= (JavaDiscountCourse) iCourse;

//        System.out.println("课程Id:"+iCourse.getId()+",课程名称:"+iCourse.getName()+",课程折扣价格:"+iCourse.getPrice() +"课程原价格:"+javaDiscountCourse.getOriginPrice());
        System.out.println("课程Id:"+iCourse.getId()+",课程名称:"+iCourse.getName()+",课程折扣价格:"+((JavaDiscountCourse) iCourse).getDiscountPrice() +"课程原价格:"+javaDiscountCourse.getPrice());

    }
}
