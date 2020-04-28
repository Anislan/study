package com.basic.design.principle.singleresponsibility;

public class Test {

    public static void main(String[] args) {
//        Bird bird = new Bird();
//        bird.mainMoveMode("大雁");
//        bird.mainMoveMode("鸵鸟");

        FlyBird bird = new FlyBird();
        bird.mainMoveMode("大雁");

        WalkBird bird1 = new WalkBird();
        bird1.mainMoveMode("鸵鸟");
    }
}
