package com.mode.technology.controller.wenqi.day01;

/**
 * @author heian
 * @date 2022/9/12 10:34
 */
public class MaoFa {

    private String color;

    private Integer maoCount;


    public MaoFa(String color, Integer maoCount) {
        this.color = color;
        this.maoCount = maoCount;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getMaoCount() {
        return maoCount;
    }

    public void setMaoCount(Integer maoCount) {
        this.maoCount = maoCount;
    }

    @Override
    public String toString() {
        return "MaoFa{" +
                "color='" + color + '\'' +
                ", maoCount=" + maoCount +
                '}';
    }

    public static void main(String[] args) {
        MaoFa m = new MaoFa("lv", 3);
        System.out.println(m.getColor());
    }

}
