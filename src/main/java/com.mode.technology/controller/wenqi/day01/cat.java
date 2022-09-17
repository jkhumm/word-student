package com.mode.technology.controller.wenqi.day01;

/**
 * @author heian
 * @date 2022/9/12 10:28
 * @description
 */
public class cat implements Animals{

    @Override
    public String eat() {
        return "mao liang";
    }

    @Override
    public String run() {
        return "null";
    }

    public static void main(String[] args) {
        cat c =new cat();
        System.out.println(c.eat());
    }
}
