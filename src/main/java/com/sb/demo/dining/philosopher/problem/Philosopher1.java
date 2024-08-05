package com.sb.demo.dining.philosopher.problem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Philosopher1 extends Thread {

    private int index;

    private String philosopherName;

    private Chopsticks left,right;

    public Philosopher1(int index, String philosopherName, Chopsticks left, Chopsticks right){
        this.setIndex(index);
        this.setPhilosopherName(philosopherName);
        this.setLeft(left);
        this.setRight(right);
    }

    @Override
    public void run() {
        synchronized (left) {
            try {
                System.out.println("哲学家：" + this.philosopherName + " 拿到左手筷子");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (right) {
                try {
                    System.out.println("哲学家：" + this.philosopherName + "，拿到右手筷子");
                    Thread.sleep(1000);
                    System.out.println("哲学家：" + this.philosopherName + "，开始吃饭");
                    System.out.println("哲学家：" + this.philosopherName + "，吃完了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
