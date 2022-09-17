package com.mode.technology.controller.wenqi.day01;

/**
 * @author heian
 * @date 2022/9/12 10:45
 * @description
 */
public class Naodai {
    private Integer size;
    private String color;

    public Integer getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Naodai(){

    }

    public Naodai(Integer size, String color) {
        this.size = size;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Naodai{" +
                "size=" + size +
                ", color='" + color + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Naodai n1 = new Naodai( 1, "lv");
        System.out.println(n1.getColor());
        System.out.println(n1.getSize());

        Naodai n = new Naodai();
        n.setColor("red");
        n.setSize(2);
        System.out.println(n.toString());

    }

}
