package com.basic.design.principle.openclose;

/**
 *  新增一个打折的类
 */
public class JavaDiscountCourse extends JavaCourse {
    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }


    // 违背里氏替换原则含义
//    public Double getOriginPrice(){
//        return super.getPrice();
//    }
//
//
//    @Override
//    public Double getPrice() {
//        return super.getPrice() * 0.8;
//    }

    public Double getDiscountPrice(){
        return super.getPrice() * 0.8;
    }



}
