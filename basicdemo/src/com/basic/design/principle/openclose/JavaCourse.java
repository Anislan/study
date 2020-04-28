package com.basic.design.principle.openclose;

public class JavaCourse implements ICourse {

    private Integer Id;
    private String name;
    private Double price;

    public JavaCourse(Integer id, String name, Double price) {
        Id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public Integer getId() {
        return this.Id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    // 修改两个点
//    @Override
//    public Double getDiscountPrice() {
//        return this.price * 0.8;
//    }
}
