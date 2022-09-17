package com.mode.technology.controller.wenqi.day01;

/**
 * @author heian
 * @date 2022/9/12 10:25
 * @description
 */
public class Dog implements Animals{

    private Naodai n;
    private MaoFa m;


    public Naodai getN() {
        return n;
    }

    public void setN(Naodai n) {
        this.n = n;
    }

    public MaoFa getM() {
        return m;
    }

    public void setM(MaoFa m) {
        this.m = m;
    }

    @Override
    public String eat() {
        return "gu tou";
    }

    @Override
    public String run() {
        return "pao";
    }


    @Override
    public String toString() {
        return "Dog{" +
                "naodai=" + n +
                ", maofa=" + m +
                '}';
    }

    public static void main(String[] args) {
//        Dog dog = new Dog();
//        dog.setM(new MaoFa("yellow",3));
//        dog.setN(new Naodai(3, "red"));
//        System.out.println(dog.toString());

        for (int i = 1; i <= 9; i++) {
            int temp = 1;
            for (int j = 1; j <= i; j++) {
                String left = temp + "x" + i + "=";
                int right = temp * i;
                System.out.print(left + right + "  ");
                if (i == temp){
                    System.out.println();
                    break;
                }else {
                    temp+=1;
                }
            }
        }
    }

}
