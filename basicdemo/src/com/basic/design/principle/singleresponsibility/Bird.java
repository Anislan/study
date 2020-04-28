package com.basic.design.principle.singleresponsibility;

public class Bird {
    public void mainMoveMode(String birdname){
        if (birdname.equals("鸵鸟")) {
            System.out.println(birdname+"用脚走");
        }else{
            System.out.println(birdname+"用翅膀飞");
        }

    }
}
